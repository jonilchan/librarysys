package com.gdufe.libsys.service.impl;

import com.gdufe.libsys.LibrarysysApplicationTests;
import com.gdufe.libsys.entity.Borrow;
import com.gdufe.libsys.mapper.BorrowMapper;
import com.gdufe.libsys.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest extends LibrarysysApplicationTests {

    @Autowired
    BorrowMapper borrowMapper;
    @Autowired
    UserService userService;
    @Test
    void fineOfUser() {
        userService.fineOfUser("1");
        List<Borrow> borrows = borrowMapper.selectByUserId("1");
        for (Borrow borrow : borrows) {
            System.out.println("=======================");
            LocalDateTime borrowTime = borrow.getBorrowTime();
            Date date = Date.from(borrowTime.atZone(ZoneId.systemDefault()).toInstant());
            Date date1 = new Date();
            long t1 = date1.getTime();
            long t2 = date.getTime();

            long millis = t2 - t1;

            long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

            System.out.println(TimeUnit.MILLISECONDS.toDays(millis));
        }
    }
}