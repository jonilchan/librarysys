package com.gdufe.libsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gdufe.libsys.entity.User;
import com.gdufe.libsys.mapper.UserMapper;
import com.gdufe.libsys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.libsys.utils.AssertUtil;
import com.gdufe.libsys.utils.Md5Util;
//import org.apache.commons.lang.StringUtils;
import com.gdufe.libsys.utils.ResultInfo;
import org.apache.commons.lang3.StringUtils;
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

    //用户登录
    @Override
    public User login(String username, String userPassword) {

        //数据库查询用户信息
        User user = userMapper.selectById(username);

        //判断用户名、密码是否为空
        AssertUtil.isTrue((username.equals("") || username == null), "用户名为空！");
        AssertUtil.isTrue((userPassword.equals("") || userPassword == null), "密码为空！");

        //检查密码是否错误
        AssertUtil.isTrue(Md5Util.encode(userPassword).equals(user.getUserPassword()), "密码错误！请检查是否输入正确");

        return user;
    }

    //修改用户密码
    @Override
    public void updateUserPassword(String userId,String userOldPassword,String newPassword,String confirmPassword) {

        //判断更新的数据是否为空值
        AssertUtil.isTrue((userOldPassword.equals("") || userOldPassword == null),"旧密码不能为空！");
        AssertUtil.isTrue((newPassword.equals("") || newPassword == null),"新密码不能为空！");
        AssertUtil.isTrue((confirmPassword.equals("") || confirmPassword == null),"确认密码不能为空！");

        //判断确认密码是否一致
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)), "确认密码与新密码不一致！");
        //如果新旧密码一致
        AssertUtil.isTrue(newPassword.equals(userOldPassword), "新旧密码一致，不必更新哦！");
        //没有携带userId则是未登录
        AssertUtil.isTrue(null == userId, "用户未登录！");
        //查看用户是否存在
        User user = userMapper.selectById(userId);
        AssertUtil.isTrue(null == user, "用户不存在！");
        //对比用户旧密码
        AssertUtil.isTrue(!(user.getUserPassword().equals(Md5Util.encode(userOldPassword))), "旧密码错误！");
        //进行更新密码操作
        user.setUserPassword(Md5Util.encode(newPassword));
    }
}
