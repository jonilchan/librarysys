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
    public ResultInfo login(String userId, String userPassword) {

        ResultInfo resultInfo = new ResultInfo();
        //数据库查询用户信息
        User user = userMapper.selectById(userId);
        if (user == null){
            resultInfo.setCode(201);
            resultInfo.setMsg("不存在该用户");
            return resultInfo;
        }
        //检查密码是否错误
        else if (!Md5Util.encode(userPassword).equals(user.getUserPassword())){
            resultInfo.setCode(201);
            resultInfo.setMsg("密码错误");
            return resultInfo;
        }
        resultInfo.setCode(200);
        resultInfo.setResult(user);
        return resultInfo;
    }

    //修改用户密码
    @Override
    public void updateUserPassword(String userId,String userOldPassword,String newPassword,String confirmPassword) {

        //判断更新的数据是否为空值
        AssertUtil.isTrue((userOldPassword.equals("") || userOldPassword == null), 202, "旧密码不能为空！");
        AssertUtil.isTrue((newPassword.equals("") || newPassword == null),202,"新密码不能为空！");
        AssertUtil.isTrue((confirmPassword.equals("") || confirmPassword == null),202,"确认密码不能为空！");
        //判断确认密码是否一致
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)), 202,"确认密码与新密码不一致！");
        //如果新旧密码一致
        AssertUtil.isTrue(newPassword.equals(userOldPassword), 202,"新旧密码一致，不必更新哦！");
        //没有携带userId则是未登录
        AssertUtil.isTrue(null == userId,202, "用户未登录！");
        //查看用户是否存在
        User user = userMapper.selectById(userId);
        AssertUtil.isTrue(null == user,202, "用户不存在！");
        //对比用户旧密码
        AssertUtil.isTrue(!(user.getUserPassword().equals(Md5Util.encode(userOldPassword))),202, "旧密码错误！");
        //进行更新密码操作
        user.setUserPassword(Md5Util.encode(newPassword));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        userMapper.update(user, wrapper);
    }
}
