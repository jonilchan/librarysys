package com.gdufe.libsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.libsys.entity.BookInfo;
import com.gdufe.libsys.entity.BookStock;
import com.gdufe.libsys.entity.Borrow;
import com.gdufe.libsys.mapper.BookInfoMapper;
import com.gdufe.libsys.mapper.BookStockMapper;
import com.gdufe.libsys.mapper.BorrowMapper;
import com.gdufe.libsys.query.BookInfoQuery;
import com.gdufe.libsys.service.BookInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.libsys.utils.AssertUtil;
import com.gdufe.libsys.vo.BookInfoVo;
import com.gdufe.libsys.vo.BookRankVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements BookInfoService {

    @Resource
    private BorrowMapper borrowMapper;

    @Autowired
    BookInfoMapper bookInfoMapper;

    @Autowired
    BookStockMapper bookStockMapper;


    //查询图书列表
    public Map<String, Object> queryBookInfosByParams(BookInfoQuery bookInfoQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(bookInfoQuery.getPage(),bookInfoQuery.getLimit());
        List<BookInfo> bookInfos = bookInfoMapper.selectByParams(bookInfoQuery);
        for (BookInfo bookInfo : bookInfos) {
            QueryWrapper<BookStock> wrapper = new QueryWrapper<>();
            wrapper.eq("isbn",bookInfo.getIsbn());
            List<BookStock> bookStocks = bookStockMapper.selectList(wrapper);
            //计算总库存
            bookInfo.setTotalStock(bookStocks.size());
            int i = 0;//计算未借阅的数数量
            int j = 0;//计算是三水还是广州
            for (BookStock bookStock : bookStocks) {
                if(bookStock.getStatus()==0){
                    i++;
                }
                if(bookStock.getBookLocation()==1){
                    j++;
                }
            }
            bookInfo.setPresentStock(i);
            if(j == 0){
                bookInfo.setBookLocation(0);
            }else if(j == bookStocks.size()){
                bookInfo.setBookLocation(1);
            }else {
                bookInfo.setBookLocation(2);
            }
        }

        PageInfo<BookInfo> pageInfo = new PageInfo<>(bookInfos);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    @Override
    public Map<String, Object> queryBookRankListByParams(BookInfoQuery bookInfoQuery) {
        Map<String, Object> map = new HashMap<>();
        //拿到前三个月的借阅记录
        List<Borrow> borrows = borrowMapper.selectByThreeMonths();
        List<BookInfo> bookInfos = bookInfoMapper.selectByParams(bookInfoQuery);
        QueryWrapper<BookStock> bookStockWrapper = new QueryWrapper();
        List<BookStock> bookStocks = bookStockMapper.selectList(bookStockWrapper);
        ArrayList<BookRankVo> bookRankVos = new ArrayList<>();
//        for (Borrow borrow : borrows) {
//            for (BookStock bookStock : bookStocks) {
//                if(borrow.getBookId() == bookStock.getBookId()){
//                    for (BookInfo bookInfo : bookInfos) {
//                        if(bookStock.getIsbn().equals(bookInfo.getIsbn())){
//                            BookRankVo bookRankVo = new BookRankVo();
//                            BeanUtils.copyProperties(bookRankVo);
//                        }
//                    }
//                }
//            }
//        }
        /**
         * 根据新表的isbn找到bookinfo对应的isbn数据
         *  BookRankVo bookRankVo = new BookRankVo();
         *  BeanUtils.copyProperties(bookRankVo);
         *  bookRankVo要set->三个月的借阅次数 borrowStockRatio
         *  再set borrowStockRatio = borrowStockRatio/total_stock
         */
        PageInfo<BookInfo> pageInfo = new PageInfo<>(bookInfos);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    //根据isBn查找图书
    @Override
    public BookInfo selectByIsbn(String isbn) {

        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(isbn);
        return bookInfo;
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
}
