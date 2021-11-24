package com.gdufe.libsys.config;

import com.gdufe.libsys.interceptor.UnLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public UnLoginInterceptor unLoginInterceptor(){
        return new UnLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(unLoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/**","/error","/index","/user/login","/css/**","/images/**","/js/**","/lib/**");
    }


}
