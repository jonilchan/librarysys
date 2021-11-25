package com.gdufe.libsys.service;

import com.gdufe.libsys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.libsys.utils.ResultInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
public interface UserService extends IService<User> {
    ResultInfo login(String username, String userPassword);
    void updateUserPassword(String userId,String userOldPassword,String newPassword,String confirmPassword);
}
