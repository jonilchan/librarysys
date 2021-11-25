package com.gdufe.libsys.controller;


import com.gdufe.libsys.entity.User;
import com.gdufe.libsys.service.UserService;
import com.gdufe.libsys.utils.LoginUserUtil;
import com.gdufe.libsys.utils.ResultInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
public class UserController extends BaseController{

    @Resource
    private UserService userService;

    //用户登录
    @PostMapping("/login")
    @ResponseBody
    public ResultInfo login(String userName,String userPassword){
        //用于放置结果信息
        ResultInfo resultInfo = new ResultInfo();
        //获取结果
        User loginResult = userService.login(userName, userPassword);
        //放置结果
        resultInfo.setResult(loginResult);
        resultInfo.setCode(200);
        //返回操作结果
        return resultInfo;
    }

    //用户更新密码
    @PostMapping("/updatePassword")
    @ResponseBody
    public ResultInfo updateUserInfo(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword){
        //用于放置结果信息
        ResultInfo resultInfo = new ResultInfo();

        //更新密码操作
//        userService.updateUserPassword(LoginUserUtil.releaseUserIdFromCookie(request), oldPassword, newPassword, confirmPassword);
        userService.updateUserPassword("1", oldPassword, newPassword, confirmPassword);

        //返回操作结果
        return resultInfo;
    }

    //更新密码页
    @GetMapping("/toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }
}

