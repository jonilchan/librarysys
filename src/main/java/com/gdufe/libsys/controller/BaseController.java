package com.gdufe.libsys.controller;


import com.gdufe.libsys.utils.ResultInfo;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;


public class BaseController {


    @ModelAttribute
    public void preHandler(HttpServletRequest request) {
        request.setAttribute("ctx", request.getContextPath());
    }


    public ResultInfo success() {
        return new ResultInfo();
    }

    public ResultInfo success(String msg) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(200);
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    public ResultInfo success(String msg, Object result) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }

}