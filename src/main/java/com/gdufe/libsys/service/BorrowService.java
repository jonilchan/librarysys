package com.gdufe.libsys.service;

import com.gdufe.libsys.entity.Borrow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.libsys.query.BorrowQuery;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
public interface BorrowService extends IService<Borrow> {
    void borrow(String userId, String isbn);

    void book(String userId, String isbn);


}
