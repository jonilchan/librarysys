package com.gdufe.libsys.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    登录状态拦截器
 */
public class UnLoginInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
        从cookie中获取id，如果id存在并且数据库有记录，就放行
         */
//        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
//        if (userId == 0 || userService.getById(userId) == null){
//            throw new UnLoginException();
//        }
//
//        return super.preHandle(request, response, handler);
        return true;
    }
}
