package com.gdufe.libsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.libsys.entity.Borrow;
import com.gdufe.libsys.entity.Reserve;
import com.gdufe.libsys.query.BorrowQuery;
import com.gdufe.libsys.query.ReserveQuery;
import com.gdufe.libsys.utils.ResultInfo;

import java.util.Map;

public interface ReserveService extends IService<Reserve> {
    Map<String, Object> queryReserveListByParams(ReserveQuery reserveQuery);

    Map<String, Object> queryReserveListByParams(ReserveQuery reserveQuery,String readerId);

    ResultInfo selectBookById(Integer bookId, String userId, Integer reserveId, String operator);

    void book(String readerId, String book_id);

    void remindBook(String borrowId);

    void cancelReserve(Integer reserveId);
}
