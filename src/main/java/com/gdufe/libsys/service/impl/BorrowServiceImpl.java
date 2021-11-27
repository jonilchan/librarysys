package com.gdufe.libsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.libsys.entity.BookStock;
import com.gdufe.libsys.entity.Borrow;
import com.gdufe.libsys.mapper.BookStockMapper;
import com.gdufe.libsys.mapper.BorrowMapper;
import com.gdufe.libsys.service.BorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.libsys.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        QueryWrapper<BookStock> wrapper = new QueryWrapper();
        //查询符合条件的借书书籍
        wrapper.eq("isbn", isbn);
        List<BookStock> bookStocks = bookStockMapper.selectList(wrapper);
        AssertUtil.isTrue(bookStocks == null, "借阅书籍库存为空");
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
}
