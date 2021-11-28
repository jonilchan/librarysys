package com.gdufe.libsys.service;

import com.gdufe.libsys.entity.BookInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.libsys.entity.BookStock;
import com.gdufe.libsys.query.BookInfoQuery;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
public interface BookInfoService extends IService<BookInfo> {

    //查询用户
    Map<String, Object> queryBookInfosByParams(BookInfoQuery bookInfoQuery);

    //根据isbn查询
    BookInfo selectByIsbn(String isbn);

    void addBookInfo(String isbn, String bookName, String author, String publisher,Integer categoryId);



}
