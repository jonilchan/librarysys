package com.gdufe.libsys.controller;


import com.gdufe.libsys.base.BaseController;
import com.gdufe.libsys.query.BookInfoQuery;
import com.gdufe.libsys.service.BookInfoService;
import com.gdufe.libsys.service.BookStockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
@RequestMapping("/book")
public class BookInfoController extends BaseController {

    @Resource
    private BookInfoService bookInfoService;

    @Resource
    private BookStockService bookStockService;

    //查询图书列表
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> queryBookInfosByParams(BookInfoQuery bookInfoQuery){
        return bookInfoService.queryBookInfosByParams(bookInfoQuery);
    }

    //图书页
    @GetMapping("/index")
    public String index(){
        return "book/book_info";
    }



}

