package com.gdufe.libsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.libsys.entity.BookInfo;
import com.gdufe.libsys.entity.BookStock;
import com.gdufe.libsys.mapper.BookInfoMapper;
import com.gdufe.libsys.mapper.BookStockMapper;
import com.gdufe.libsys.mapper.BorrowMapper;
import com.gdufe.libsys.query.BookInfoQuery;
import com.gdufe.libsys.service.BookInfoService;
import com.gdufe.libsys.utils.AssertUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
@Service
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements BookInfoService {

    @Autowired
    BookInfoMapper bookInfoMapper;

    @Autowired
    BookStockMapper bookStockMapper;


    //查询图书列表
    public Map<String, Object> queryBookInfosByParams(BookInfoQuery bookInfoQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(bookInfoQuery.getPage(), bookInfoQuery.getLimit());
        PageInfo<BookInfo> pageInfo = new PageInfo<>(getBookInfos(bookInfoQuery));
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    //把图书信息封装成一个方法
    public List<BookInfo> getBookInfos(BookInfoQuery bookInfoQuery) {
        QueryWrapper<BookInfo> queryWrapper = new QueryWrapper<>();
        if (bookInfoQuery.getBookName() != null && bookInfoQuery.getBookName() != ""){
            queryWrapper.like("book_name", bookInfoQuery.getBookName());
        }
        if (bookInfoQuery.getIsbn() != null && bookInfoQuery.getIsbn() != ""){
            queryWrapper.like("isbn", bookInfoQuery.getIsbn());
        }
        if (bookInfoQuery.getAuthor() != null && bookInfoQuery.getAuthor() != ""){
            queryWrapper.like("author", bookInfoQuery.getAuthor());
        }
        List<BookInfo> bookInfos = bookInfoMapper.selectList(queryWrapper);
        for (BookInfo bookInfo : bookInfos) {
            QueryWrapper<BookStock> wrapper = new QueryWrapper<>();
            wrapper.eq("isbn", bookInfo.getIsbn());
            List<BookStock> bookStocks = bookStockMapper.selectList(wrapper);
            //计算总库存
            bookInfo.setTotalStock(bookStocks.size());
            int i = 0;//计算未借阅的数数量
            int j = 0;//计算是三水还是广州
            for (BookStock bookStock : bookStocks) {
                if (bookStock.getStatus() == 0) {
                    i++;
                }
                if (bookStock.getBookLocation() == 1) {
                    j++;
                }
            }
            bookInfo.setPresentStock(i);
            if (j == 0) {
                bookInfo.setBookLocation(0);
            } else if (j == bookStocks.size()) {
                bookInfo.setBookLocation(1);
            } else {
                bookInfo.setBookLocation(2);
            }
            bookInfoMapper.updateById(bookInfo);
        }
        return bookInfos;
    }

    @Override
    public void addBookInfo(String isbn, String bookName, String author, String publisher, Integer categoryId) {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setIsbn(isbn);
        bookInfo.setBookName(bookName);
        bookInfo.setAuthor(author);
        bookInfo.setPublisher(publisher);
        bookInfo.setStatus(0);
        bookInfo.setCategoryId(categoryId);
        bookInfo.setEnterTime(LocalDateTime.now());
        AssertUtil.isTrue(bookInfoMapper.insert(bookInfo) < 1, 201, "添加用户失败");
    }

    @Override
    public void updateBookInfo(String isbn, String bookName, String author, String publisher, Integer categoryId) {
        BookInfo bookInfo = bookInfoMapper.selectById(isbn);
        bookInfo.setBookName(bookName);
        bookInfo.setAuthor(author);
        bookInfo.setPublisher(publisher);
        bookInfo.setCategoryId(categoryId);
        bookInfo.setUpdateTime(LocalDateTime.now());
        AssertUtil.isTrue(bookInfoMapper.updateById(bookInfo) < 1, 201, "编辑失败");
    }

    @Override
    public void stopBookBorrw(String isbn) {
        BookInfo bookInfo = bookInfoMapper.selectById(isbn);
        bookInfo.setStatus(1);
        bookInfoMapper.updateById(bookInfo);
    }
}
