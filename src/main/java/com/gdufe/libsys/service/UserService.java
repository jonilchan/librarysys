package com.gdufe.libsys.service;

import com.gdufe.libsys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.libsys.query.UserQuery;
import com.gdufe.libsys.utils.ResultInfo;

import java.util.Map;

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
    void updateInfo(String userId, String username, String phone);
    void addUser(String userId, String userName, String userPassword, String phone, Integer identity);
    void updateUser(String userId, String userName, String userPassword, String phone, Integer identity, Integer status);
    //查询用户
    Map<String, Object> queryUsersByParams(UserQuery userQuery);
}
