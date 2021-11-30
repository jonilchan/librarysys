package com.gdufe.libsys.controller;

import com.gdufe.libsys.base.BaseController;
import com.gdufe.libsys.entity.User;
import com.gdufe.libsys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;

    //用户登录页
    @GetMapping("index")
    public String index(){
        return "index";
    }


    //系统欢迎页
    @GetMapping("welcome")
    public String welcome(HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        User user = userService.getById(userId);
        request.setAttribute("user", user);
        String[] identity = {"学生", "老师", "图书管理员", "系统管理员"};
        String[] status = {"正常", "挂失", "注销", "暂停借阅"};
        request.setAttribute("identity", identity[user.getIdentity()]);
        request.setAttribute("status", status[user.getStatus()]);
        Double fine = userService.fineOfUser(userId);
        request.setAttribute("fine", String.valueOf(fine).substring(0, (int) (Math.round(fine/10)+1)));
        return "welcome";
    }

    //后台管理页
    @GetMapping(value = {"/","main"})
    public String admin(HttpServletRequest request) {
        String userId = request.getSession().getAttribute("userId").toString();
        User user = userService.getById(userId);
        request.setAttribute("user", user);
        return "main";
    }
}
