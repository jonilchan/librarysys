package com.gdufe.libsys.service.impl;

import com.gdufe.libsys.LibrarysysApplicationTests;
import com.gdufe.libsys.service.BookStockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

class BookStockServiceImplTest extends LibrarysysApplicationTests {

    @Autowired
    BookStockService bookStockService;
    @Test
    @Transactional
    void addStock() {
        bookStockService.reduceStock("122",1,0);
    }
}