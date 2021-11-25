package com.gdufe.libsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.libsys.entity.User;
import com.gdufe.libsys.service.UserService;
import com.gdufe.libsys.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;


    @GetMapping("index")
    public String index(){
        return "index";
    }


    //系统欢迎页
    @GetMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    //后台管理页
    @GetMapping(value = {"/","main"})
    public String admin(HttpServletRequest request) {
//        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.getById(userId);
        request.setAttribute("user", user);
        request.setAttribute("userId", userId);
        return "main";
    }
}
