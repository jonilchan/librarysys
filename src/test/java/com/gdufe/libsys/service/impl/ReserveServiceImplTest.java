package com.gdufe.libsys.service.impl;

import com.gdufe.libsys.LibrarysysApplicationTests;
import com.gdufe.libsys.query.ReserveQuery;
import com.gdufe.libsys.service.ReserveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

//@Transactional
class ReserveServiceImplTest extends LibrarysysApplicationTests {

    @Autowired
    ReserveService reserveService;
    @Test
    void queryReserveListByParams() {
//        reserveService.selectBookById(1,"1");
    }
}