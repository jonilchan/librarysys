package com.gdufe.libsys.controller;


import com.gdufe.libsys.base.BaseController;
import com.gdufe.libsys.service.BorrowService;
import com.gdufe.libsys.utils.ResultInfo;
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
@RequestMapping("/borrow")
public class BorrowController extends BaseController {

    @Resource
    private BorrowService borrowService;


    //借书
    @RequestMapping("/borrow")
    @ResponseBody
    public ResultInfo borrow(HttpServletRequest request, String isbn){
        borrowService.borrow(request.getSession().getAttribute("userId").toString(), isbn);
        return new ResultInfo(200);
    }

    //预约
    @RequestMapping("/book")
    @ResponseBody
    public ResultInfo book(HttpServletRequest request, String isbn){
        borrowService.book(request.getSession().getAttribute("userId").toString(), isbn);
        return new ResultInfo(200);
    }

    @RequestMapping("/list")
    public

    //借书、预约页
    @RequestMapping("/index")
    public String borrowIndex(){
        return "/borrow/borrow";
    }

    @RequestMapping("giveback")
    public String giveback(){
        return "/borrow/giveback";
    }
}

