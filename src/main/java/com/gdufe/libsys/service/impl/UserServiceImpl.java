package com.gdufe.libsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.libsys.entity.Borrow;
import com.gdufe.libsys.entity.User;
import com.gdufe.libsys.mapper.BorrowMapper;
import com.gdufe.libsys.mapper.UserMapper;
import com.gdufe.libsys.query.UserQuery;
import com.gdufe.libsys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.libsys.utils.AssertUtil;
import com.gdufe.libsys.utils.Md5Util;
//import org.apache.commons.lang.StringUtils;
import com.gdufe.libsys.utils.ResultInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private BorrowMapper borrowMapper;

    //用户登录
    @Override
    public ResultInfo login(String userId, String userPassword) {
        ResultInfo resultInfo = new ResultInfo();
        //数据库查询用户信息
        User user = userMapper.selectById(userId);
        AssertUtil.isTrue(null == user,"用户不存在！");
        //检查密码是否错误
        AssertUtil.isTrue(!(user.getUserPassword().equals(Md5Util.encode(userPassword))),"密码错误！请检查是否输入正确");
        resultInfo.setCode(200);
        resultInfo.setResult(user);
        return resultInfo;
    }

    //修改用户密码
    @Override
    public void updateUserPassword(String userId,String userOldPassword,String newPassword,String confirmPassword) {

        //判断更新的数据是否为空值
        AssertUtil.isTrue((userOldPassword.equals("") || userOldPassword == null),  "旧密码不能为空！");
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
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        userMapper.update(user, wrapper);
    }

    @Override
    public void updateInfo(String userId, String username, String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        User user = userMapper.selectById(userId);
        user.setUserName(username);
        user.setPhone(phone);
        userMapper.updateById(user);
    }

    @Override
    public void addUser(String userId, String userName, String userPassword, String phone, Integer identity) {
        //生成用户对象并补全信息
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserPassword(Md5Util.encode(userPassword));
        user.setPhone(phone);
        user.setIdentity(identity);
        user.setCreateTime(LocalDateTime.now());
        AssertUtil.isTrue(userMapper.insert(user) < 1, 201, "添加用户失败");
    }

    @Override
    public void updateUser(String userId, String userName, String userPassword, String phone, Integer identity, Integer status) {
        User user = userMapper.selectById(userId);
        user.setUserName(userName);
        user.setPhone(phone);
        user.setIdentity(identity);
        user.setStatus(status);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }

    @Override
    public Map<String, Object> queryUsersByParams(UserQuery userQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(userQuery.getPage(),userQuery.getLimit());
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectByParams(userQuery));
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    @Override
    public double fineOfUser(String userId) {
        QueryWrapper<Borrow> wrapper = new QueryWrapper<>();
        wrapper.eq("reader_id", userId);
        List<Borrow> borrows = borrowMapper.selectList(wrapper);
        //查询借阅日期大于30的
        int totalFineDay=  0;
        for (Borrow borrow : borrows) {
            LocalDateTime borrowTime = borrow.getBorrowTime();
            Date borrowT = Date.from(borrowTime.atZone(ZoneId.systemDefault()).toInstant());
            Date currentT = new Date();
            long c = currentT.getTime();
            long b = borrowT.getTime();
            long millis = c - b;
            int borrowDay = (int)TimeUnit.MILLISECONDS.toDays(millis)-30;
            if(borrowDay > 0){
                totalFineDay+=borrowDay;
            }
        }
        double fineMoney = totalFineDay * 0.1;
        return fineMoney;
    }
}
