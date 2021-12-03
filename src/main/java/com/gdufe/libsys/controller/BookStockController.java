package com.gdufe.libsys.controller;


import com.gdufe.libsys.base.BaseController;
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
        request.setAttribute("isbn",isbn);
        this.isbn = isbn;
        return "bookStock/book_stock";
    }

    //查询该isbn下的图书库存
    @GetMapping("list")
    @ResponseBody
    public Map<String, Object> queryBookStocksByParams(BookStockQuery bookStockQuery) {
        bookStockQuery.setIsbn(isbn);
        return bookStockService.selectAll(bookStockQuery);
    }

    //增加库存
    @GetMapping("toAddStock")
    public String toAddStock(String isbn, Model model){
//        model.addAttribute("bookinfo",bookInfoService.selectByIsbn(isbn));
        //System.out.println(customerService.getById(id));
        return "bookStock/add_stock";
    }

    //减少库存
    @GetMapping("toReduceStock")
    public String toReduceStock(String isbn, Model model){
//        model.addAttribute("bookinfo",bookInfoService.selectByIsbn(isbn));
        //System.out.println(customerService.getById(id));
        return "bookStock/reduce_stock";
    }
}

