package com.gdufe.libsys.service.impl;


import com.gdufe.libsys.LibrarysysApplicationTests;
import com.gdufe.libsys.query.BookInfoQuery;
import com.gdufe.libsys.service.BookInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

class BookInfoServiceImplTest extends LibrarysysApplicationTests {
    @Autowired
    BookInfoService bookInfoService;

    @Test
    void queryBookRankListByParams() {
        BookInfoQuery bookInfoQuery = new BookInfoQuery();
        bookInfoService.queryBookRankListByParams(bookInfoQuery);
    }
}