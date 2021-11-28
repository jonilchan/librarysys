package com.gdufe.libsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.libsys.entity.BookInfo;
import com.gdufe.libsys.entity.BookStock;
import com.gdufe.libsys.entity.Borrow;
import com.gdufe.libsys.entity.User;
import com.gdufe.libsys.mapper.BookStockMapper;
import com.gdufe.libsys.mapper.BorrowMapper;
import com.gdufe.libsys.mapper.UserMapper;
import com.gdufe.libsys.query.BookInfoQuery;
import com.gdufe.libsys.query.BorrowQuery;
import com.gdufe.libsys.service.BorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.libsys.utils.AssertUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
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

    //借书
    @Override
    public void borrow(String userId, String isbn) {

        QueryWrapper<BookStock> wrapper = new QueryWrapper();

        //查询符合条件的借书书籍
        wrapper.eq("isbn", isbn).eq("status", 0);
        List<BookStock> bookStocks = bookStockMapper.selectList(wrapper);
        AssertUtil.isTrue(bookStocks == null, "借阅书籍库存为空");
        //填充borrow数据，生成订单
        BookStock bookStock = bookStocks.get(0);
        Borrow borrow = new Borrow();
        borrow.setBookId(bookStock.getBookId());
        borrow.setReaderId(userId);
        borrow.setStatus(0);
        borrowMapper.insert(borrow);
        //更新被借书的状态
        bookStock.setStatus(1);
        bookStockMapper.updateById(bookStock);
    }

    //预约
    @Override
    public void book(String userId, String isbn) {
        //查询符合条件的借书书籍
        QueryWrapper<BookStock> bookStockWrapper = new QueryWrapper();
        bookStockWrapper.eq("isbn", isbn);
        List<BookStock> bookStocks = bookStockMapper.selectList(bookStockWrapper);
        AssertUtil.isTrue(bookStocks == null, "借阅书籍库存为空");
        //查看借阅是否达到上线
        QueryWrapper<Borrow> borrowQueryWrapper = new QueryWrapper<>();
        borrowQueryWrapper.eq("reader_id", userId);
        List<Borrow> borrowList = borrowMapper.selectList(borrowQueryWrapper);
        User user = userMapper.selectById(userId);
        Integer size = borrowList.size();
        Integer identity = user.getIdentity();
        AssertUtil.isTrue(size == 5 && identity == 0, "借阅数量达到上线");
        AssertUtil.isTrue(size == 20 && identity == 1, "借阅数量达到上线");
        AssertUtil.isTrue(size == 20 && identity == 2, "借阅数量达到上线");
        AssertUtil.isTrue(size == 20 && identity == 3, "借阅数量达到上线");
        //填充borrow数据，生成订单
        BookStock bookStock = bookStocks.get(0);
        Borrow borrow = new Borrow();
        borrow.setBookId(bookStock.getBookId());
        borrow.setReaderId(userId);
        borrow.setStatus(1);
        borrowMapper.insert(borrow);
        //更新被借书的状态
        bookStock.setStatus(1);
        bookStockMapper.updateById(bookStock);
    }

    //查询图书列表
    public Map<String, Object> queryBorrowsByParams(BorrowQuery borrowQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(borrowQuery.getPage(),borrowQuery.getLimit());
        PageInfo<Borrow> pageInfo = new PageInfo<>(borrowMapper.selectByParams(borrowQuery));
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
}
