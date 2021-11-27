package com.gdufe.libsys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.libsys.base.BaseController;
import com.gdufe.libsys.entity.BookStock;
import com.gdufe.libsys.query.BookStockQuery;
import com.gdufe.libsys.service.BookStockService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/bookStock")
public class BookStockController extends BaseController {

    String isbn;
    @Resource
    private BookStockService bookStockService;

    @GetMapping("/info")
    public String bookStockInfo(String isbn, HttpServletRequest request){
//        model.addAttribute("customerContact",bookStockService.getOne(new QueryWrapper<BookStock>().eq("cus_id",cid)));
//        model.addAttribute("cusId",cid);
        System.out.println("=============="+isbn);
        request.setAttribute("isbn",isbn);
        this.isbn = isbn;
        return "bookStock/user";
//        return "bookStock/user";
    }

    //查询所有交往记录
    @GetMapping("list")
    @ResponseBody
    public Map<String, Object> queryBookStocksByParams(BookStockQuery bookStockQuery) {
        bookStockQuery.setIsbn(isbn);
        return bookStockService.selectAll(bookStockQuery);
    }
}

