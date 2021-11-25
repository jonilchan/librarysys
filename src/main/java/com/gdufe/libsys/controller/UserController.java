package com.gdufe.libsys.controller;

import com.gdufe.libsys.service.UserService;
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
    public ResultInfo login(HttpServletRequest request, String userName, String userPassword){
        //获取结果
        ResultInfo loginResult = userService.login(userName, userPassword);
        //设置cookie
        request.setAttribute("user", loginResult.getResult());
        //设置session
        request.getSession().setAttribute("userId", userName);
        //返回操作结果
        return loginResult;
    }

    //用户更新密码
    @PostMapping("/updatePassword")
    @ResponseBody
    public ResultInfo updateUserInfo(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword){
        //用于放置结果信息
        ResultInfo resultInfo = new ResultInfo();
        //更新密码操作
        userService.updateUserPassword(request.getSession().getAttribute("userId").toString(), oldPassword, newPassword, confirmPassword);
        //返回操作结果
        return resultInfo;
    }

    //更新密码页
    @GetMapping("/toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }
}

