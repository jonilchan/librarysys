package com.gdufe.libsys.controller;


import com.gdufe.libsys.entity.User;
import com.gdufe.libsys.service.UserService;
import com.gdufe.libsys.utils.ResultInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    //用户登录
    @PostMapping("/login")
    @ResponseBody
    public ResultInfo login(String username,String userPassword){
        //用于放置结果信息
        ResultInfo resultInfo = new ResultInfo();

        //获取结果
        User loginResult = userService.login(username, userPassword);
        //放置结果
        resultInfo.setResult(loginResult);
        //返回操作结果
        return resultInfo;
    }
}

