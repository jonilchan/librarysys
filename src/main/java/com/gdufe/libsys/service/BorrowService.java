package com.gdufe.libsys.service;

import com.gdufe.libsys.entity.Borrow;
import com.baomidou.mybatisplus.extension.service.IService;

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
