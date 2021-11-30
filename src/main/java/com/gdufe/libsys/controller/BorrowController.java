package com.gdufe.libsys.controller;


import com.gdufe.libsys.base.BaseController;
import com.gdufe.libsys.query.BorrowQuery;
import com.gdufe.libsys.service.BorrowService;
import com.gdufe.libsys.utils.ResultInfo;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

    //借阅表查询
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> queryBorrowByParams(BorrowQuery borrowQuery){
        return borrowService.queryBorrowsByParams(borrowQuery);
    }

    //借书、预约页
    @RequestMapping("/toBorrowPage")
    public String toBorrowPage(){
        return "/borrow/borrow";
    }


    //归还图书
    @RequestMapping("/giveback")
    @ResponseBody
    public ResultInfo giveback(Integer borrowId){
        borrowService.giveback(borrowId);
        return new ResultInfo(200);
    }

    //续借
    @RequestMapping("/renew")
    @ResponseBody
    public ResultInfo renew(Integer borrowId){
        borrowService.renew(borrowId);
        return new ResultInfo(200);
    }

    //借阅管理页面
    @RequestMapping("/toManagePage")
    public String toManagePage(){
        return "/borrow/borrow_list";
    }

    @RequestMapping("/toRenewPage")
    public String toRenewPage(){
        return "/borrow/my_borrow";
    }
}

