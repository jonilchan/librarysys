package com.gdufe.libsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.libsys.base.BorrowStatusEnum;
import com.gdufe.libsys.base.Statistic;
import com.gdufe.libsys.entity.*;
import com.gdufe.libsys.mapper.*;
import com.gdufe.libsys.query.BorrowQuery;
import com.gdufe.libsys.service.BorrowService;
import com.gdufe.libsys.utils.AssertUtil;
import com.gdufe.libsys.vo.BorrowVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {

    @Resource
    private BorrowMapper borrowMapper;

    @Resource
    private BookStockMapper bookStockMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private BookInfoMapper bookInfoMapper;

    @Resource
    private BookRankMapper bookRankMapper;

    @Resource
    private UserMsgMapper userMsgMapper;

    //借书
    @Override
    @Transactional
    public void borrow(String readerId, Integer bookId, String userId) {

        //填充Borrow数据
        Borrow borrow = new Borrow();
        borrow.setReaderId(readerId);
        borrow.setOperator(userMapper.selectById(userId).getUserName());
        borrow.setBookId(bookId);
        borrow.setStatus(BorrowStatusEnum.已借未还.getCode());
        AssertUtil.isTrue(borrowMapper.insert(borrow) != 1, "借阅书籍失败");
        //填充Rank表
        BookStock bookStock = bookStockMapper.selectById(bookId);
        BookRank bookRank = new BookRank();
        bookRank.setBookId(bookId);
        bookRank.setIsbn(bookStock.getIsbn());
        bookRank.setReaderId(readerId);
//        bookRank.setBorrowTime(LocalDateTime.now());
        bookRankMapper.insert(bookRank);
        //更新被借书的状态
        bookStock.setStatus(1);
        bookStockMapper.updateById(bookStock);
        //book_info减少库存
        BookInfo bookInfo = bookInfoMapper.selectById(bookStock.getIsbn());
        bookInfo.setPresentStock(bookInfo.getPresentStock() - 1);
        bookInfoMapper.updateById(bookInfo);

        Statistic.borrow();
    }

    //预约
    @Override
    @Transactional
    public void book(String userId, String isbn) {
        //查询符合条件的借书书籍
        QueryWrapper<BookStock> bookStockWrapper = new QueryWrapper();
        bookStockWrapper.eq("isbn", isbn);
        BookInfo bookInfo = bookInfoMapper.selectById("isbn");
        List<BookStock> bookStocks = bookStockMapper.selectList(bookStockWrapper);
        AssertUtil.isTrue(bookStocks == null, "借阅书籍库存为空");
        //查看借阅是否达到上限
        QueryWrapper<Borrow> borrowQueryWrapper = new QueryWrapper<>();
        borrowQueryWrapper.eq("reader_id", userId);
        List<Borrow> borrowList = borrowMapper.selectList(borrowQueryWrapper);
        User user = userMapper.selectById(userId);
        Integer size = borrowList.size();
        Integer identity = user.getIdentity();
        AssertUtil.isTrue(bookInfo.getStatus() == 1, "该书暂停借阅！");
        AssertUtil.isTrue(size == 5 && identity == 0, "借阅数量达到上限");
        AssertUtil.isTrue(size == 20 && identity == 1, "借阅数量达到上限");
        //填充borrow数据，生成订单
        BookStock bookStock = bookStocks.get(0);
        Borrow borrow = new Borrow();
        borrow.setBookId(bookStock.getBookId());
        borrow.setReaderId(userId);
        borrow.setStatus(BorrowStatusEnum.预约未拿.getCode());
        borrowMapper.insert(borrow);
        //更新被借书的状态
        bookStock.setStatus(1);
        bookStockMapper.updateById(bookStock);

        Statistic.reverse();
    }


    //归还
    @Override
    public void giveback(Integer borrowId) {
        Borrow borrow = borrowMapper.selectById(borrowId);
        borrow.setReturnTime(LocalDateTime.now());
        AssertUtil.isTrue(borrow.getStatus() == 1, "当前书籍已归还");
        borrow.setStatus(1);
        BookStock bookStock = bookStockMapper.selectById(borrow.getBookId());
        bookStock.setStatus(0);

        bookStockMapper.updateById(bookStock);
        borrowMapper.updateById(borrow);

        //book_info增加库存
        BookInfo bookInfo = bookInfoMapper.selectById(bookStock.getIsbn());
        bookInfo.setPresentStock(bookInfo.getPresentStock() + 1);
        bookInfoMapper.updateById(bookInfo);
    }


    //催还
    @Override
    public void urgereturn(Integer borrowId) {
        UserMsg userMsg = new UserMsg();
        Borrow borrow = borrowMapper.selectById(borrowId);
        AssertUtil.isTrue(borrow.getStatus() == BorrowStatusEnum.已还.getCode(), "本书籍已归还！");
        BookInfo bookInfo = bookInfoMapper.selectById(bookStockMapper.selectById(borrow.getBookId()).getIsbn());
        userMsg.setUserId(borrow.getReaderId());
        userMsg.setCreateTime(LocalDateTime.now());
        userMsg.setMsg("您的书籍《" + bookInfo.getBookName() + "》已超时未还！请尽快到图书馆归还！");
        userMsgMapper.insert(userMsg);
    }

    @Override
    public void renewBorrow(Integer borrowId) {
        Borrow borrow = borrowMapper.selectById(borrowId);
        AssertUtil.isTrue(borrow.getStatus() == 1, "该书已经归还！");
        AssertUtil.isTrue(borrow.getRenew() == 1, "该书已经续借！！");
        AssertUtil.isTrue(borrow == null, "不存在该订单！");
        borrow.setRenew(1);
        borrowMapper.updateById(borrow);
    }


    //查询借阅记录（计算罚款）
    public Map<String, Object> queryBorrowsByParams(BorrowQuery borrowQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(borrowQuery.getPage(), borrowQuery.getLimit());
        List<Borrow> borrows = borrowMapper.selectByParams(borrowQuery);
        ArrayList<BorrowVo> borrowVos = new ArrayList<>();
        for (Borrow borrow : borrows) {
            BorrowVo borrowVo = new BorrowVo();
            LocalDateTime borrowTime = borrow.getBorrowTime();
            Date borrowT = Date.from(borrowTime.atZone(ZoneId.systemDefault()).toInstant());
            Date currentT = new Date();
            long c = currentT.getTime();
            long b = borrowT.getTime();
            long millis = c - b;
            int borrowDay = 0;
            if (borrow.getRenew() == 1) {
                borrowDay = (int) TimeUnit.MILLISECONDS.toDays(millis) - 60;
            } else {
                borrowDay = (int) TimeUnit.MILLISECONDS.toDays(millis) - 30;
            }

            if (borrowDay > 0) {
                double fine = borrowDay * 0.1;
                borrow.setFine(fine);
                borrowMapper.updateById(borrow);
            }
            BeanUtils.copyProperties(borrow, borrowVo);
            BookStock bookStock = bookStockMapper.selectById(borrow.getBookId());
            BookInfo bookInfo = bookInfoMapper.selectById(bookStock.getIsbn());
            borrowVo.setBookName(bookInfo.getBookName());
            borrowVos.add(borrowVo);
        }
        //先更新 再查询
//        PageInfo<Borrow> pageInfo = new PageInfo<>(borrowMapper.selectByParams(borrowQuery));
        PageInfo<BorrowVo> pageInfo = new PageInfo<>(borrowVos);
        PageInfo<Borrow> pageInfo1 = new PageInfo<>(borrows);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo1.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }


}
