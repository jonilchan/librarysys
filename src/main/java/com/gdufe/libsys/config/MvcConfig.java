package com.gdufe.libsys.config;

import com.gdufe.libsys.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    //拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //创建拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();

        //配置白名单
        List<String> whiteList = new ArrayList<>();
        whiteList.add(("/view/index.ftl"));
        whiteList.add(("/view/common.ftl"));
        whiteList.add(("/view/error.ftl"));
        whiteList.add("/public/**");

        //拦截器注册
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(whiteList);

    }
}
