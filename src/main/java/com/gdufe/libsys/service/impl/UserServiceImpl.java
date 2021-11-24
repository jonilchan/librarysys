package com.gdufe.libsys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gdufe.libsys.entity.User;
import com.gdufe.libsys.mapper.UserMapper;
import com.gdufe.libsys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.libsys.utils.AssertUtil;
import com.gdufe.libsys.utils.Md5Util;
//import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String username, String userPassword) {
        User user = userMapper.selectById(username);
//        AssertUtil.isTrue(Md5Util.encode(userPassword).equals(user.getUserPassword()), "密码错误！请检查是否输入正确");
//        AssertUtil.isTrue(userPassword.equals(user.getUserPassword()), "密码错误！请检查是否输入正确");
        if (userPassword.equals(user.getUserPassword())){
            return user;
        }
        return null;
    }

    //判断用户名、密码是否为空
//    private void checkLoginParams(String userName, String userPassword) {
//        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名为空！");
//        AssertUtil.isTrue(StringUtils.isBlank(userPassword),"密码为空！");
//    }
}
