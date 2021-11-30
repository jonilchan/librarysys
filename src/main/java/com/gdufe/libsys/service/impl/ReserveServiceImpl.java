package com.gdufe.libsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.libsys.base.BorrowStatusEnum;
import com.gdufe.libsys.base.ReserveStatusEnum;
import com.gdufe.libsys.entity.*;
import com.gdufe.libsys.mapper.*;
import com.gdufe.libsys.query.BookInfoQuery;
import com.gdufe.libsys.query.BorrowQuery;
import com.gdufe.libsys.query.ReserveQuery;
import com.gdufe.libsys.service.ReserveService;
import com.gdufe.libsys.utils.AssertUtil;
import com.gdufe.libsys.utils.ResultInfo;
import com.gdufe.libsys.vo.ReserveVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReserveServiceImpl extends ServiceImpl<ReserveMapper, Reserve> implements ReserveService {

    @Autowired
    private ReserveMapper reserveMapper;

    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private BookStockMapper bookStockMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookInfoServiceImpl bookInfoService;

    @Autowired
    private RankMapper rankMapper;

    @Override
    public Map<String, Object> queryReserveListByParams(ReserveQuery reserveQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(reserveQuery.getPage(),reserveQuery.getLimit());
        List<Reserve> reserves = reserveMapper.selectByParams(reserveQuery);
        List<BookInfo> bookInfos = bookInfoService.getBookInfos(new BookInfoQuery());
        ArrayList<ReserveVo> reserveVos = new ArrayList<>();
        for (Reserve reserve : reserves) {
            for (BookInfo bookInfo : bookInfos) {
                if(reserve.getIsbn().equals(bookInfo.getIsbn())){
                    ReserveVo reserveVo = new ReserveVo();
                    BeanUtils.copyProperties(bookInfo,reserveVo);
                    BeanUtils.copyProperties(reserve,reserveVo);
                    reserveVos.add(reserveVo);
                    break;
                }
            }
        }
        PageInfo<ReserveVo> pageInfo = new PageInfo<>(reserveVos);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }


    //选择预约书籍
    @Override
    public ResultInfo selectBookById(Integer bookId, String readerId, Integer reserveId, String operator) {

        Reserve reserve = reserveMapper.selectById(reserveId);
        if(reserve.getStatus() == 1){
            return new ResultInfo(20);
        }

        //查看借阅是否达到上限
        QueryWrapper<Borrow> borrowQueryWrapper = new QueryWrapper<>();
        borrowQueryWrapper.eq("reader_id", readerId);
        List<Borrow> borrowList = borrowMapper.selectList(borrowQueryWrapper);
        User user = userMapper.selectById(readerId);
        Integer size = borrowList.size();
        Integer identity = user.getIdentity();
        AssertUtil.isTrue(size == 5 && identity == 0, "借阅数量达到上限");
        AssertUtil.isTrue(size == 20 && identity == 1, "借阅数量达到上限");

        //填充Rank表
        Rank rank = new Rank();
        rank.setBookId(bookId);
        rank.setIsbn(bookStockMapper.selectById(bookId).getIsbn());
        rank.setReaderId(readerId);

        //填充borrow
        Borrow borrow = new Borrow();
        borrow.setBookId(bookId);
        borrow.setReaderId(readerId);
        //操作员
        borrow.setOperator(operator);

        borrow.setStatus(BorrowStatusEnum.已借未换.getCode());
        borrowMapper.insert(borrow);
        QueryWrapper<BookStock> bookStockQueryWrapper = new QueryWrapper<>();
        bookStockQueryWrapper.eq("book_id",bookId);
        BookStock bookStock = bookStockMapper.selectOne(bookStockQueryWrapper);
        bookStock.setStatus(1);
        bookStockMapper.updateById(bookStock);
        reserve.setStatus(ReserveStatusEnum.已取书.getCode());
        reserveMapper.updateById(reserve);
        return new ResultInfo(200);
    }
}
