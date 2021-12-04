package com.gdufe.libsys.controller;


import com.gdufe.libsys.base.BaseController;
import com.gdufe.libsys.query.BookStockQuery;
import com.gdufe.libsys.service.BookStockService;
import com.gdufe.libsys.utils.ResultInfo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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


    @Resource
    private BookStockService bookStockService;


    @GetMapping("/info")
    public String bookStockInfo(String isbn, HttpServletRequest request){
        request.getSession().setAttribute("isbn",isbn);
//        request.setAttribute("isbn",isbn);
//        this.isbn = isbn;
        return "bookStock/book_stock";
    }

    //查询该isbn下的图书库存
    @GetMapping("list")
    @ResponseBody
    public Map<String, Object> queryBookStocksByParams(BookStockQuery bookStockQuery, HttpServletRequest request) {
        bookStockQuery.setIsbn((String) request.getSession().getAttribute("isbn"));
        return bookStockService.selectAll(bookStockQuery);
    }

    //增加库存
    @GetMapping("toAddStock")
    public String toAddStock(String isbn, Model model){
//        model.addAttribute("bookinfo",bookInfoService.selectByIsbn(isbn));
        //System.out.println(customerService.getById(id));
        return "bookStock/add_stock";
    }

    @RequestMapping("addStock")
    @ResponseBody
    public ResultInfo addStock(HttpServletRequest request,Integer bookLocation,Integer amount){
        String isbn = (String) request.getSession().getAttribute("isbn");
        bookStockService.addStock(isbn,amount,bookLocation);
        return new ResultInfo(200);
    }
    //减少库存
    @GetMapping("toReduceStock")
    public String toReduceStock(String isbn, Model model){
//        model.addAttribute("bookinfo",bookInfoService.selectByIsbn(isbn));
        //System.out.println(customerService.getById(id));
        return "bookStock/reduce_stock";
    }

    @RequestMapping("reduceStock")
    @ResponseBody
    public ResultInfo reduceStock(HttpServletRequest request,Integer bookLocation,Integer amount){
        String isbn = (String) request.getSession().getAttribute("isbn");
        bookStockService.reduceStock(isbn,amount,bookLocation);
        return new ResultInfo(200);
    }

    @RequestMapping("deleteStock")
    @ResponseBody
    public ResultInfo deleteStock(Integer bookId){
        bookStockService.deleBook(bookId);
        return new ResultInfo(200);
    }

    //转移馆藏三水
    @PostMapping("/transferToSS")
    @ResponseBody
    public ResultInfo transferToSS(Integer[] ids){
        bookStockService.transferToSS(ids);
        return success("转移馆藏到三水成功");
    }

    //转移馆藏广州
    @PostMapping("/transferToGZ")
    @ResponseBody
    public ResultInfo transferToGZ(Integer[] ids){
        bookStockService.transferToGZ(ids);
        return success("转移图书到广州成功");
    }


}

