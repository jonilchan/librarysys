package com.gdufe.libsys.controller;


import com.gdufe.libsys.base.BaseController;
import com.gdufe.libsys.query.BookInfoQuery;
import com.gdufe.libsys.service.BookInfoService;
import com.gdufe.libsys.service.BookStockService;
import com.gdufe.libsys.utils.ResultInfo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Iterator;
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

//    @GetMapping("/toAddBook")
//    public String toAddUserPage(){
//        return "book/add_book";
//    }

    //增加图书
    @GetMapping("toAddBook")
    public String toAddBookPage(String isbn, Model model){
//        model.addAttribute("bookinfo",bookInfoService.selectByIsbn(isbn));
        //System.out.println(customerService.getById(id));
        return "book/add_book";
    }

    @RequestMapping("/addBook")
    @ResponseBody
    ResultInfo addBook(String isbn, String bookName, String author, String publisher,Integer category){
        bookInfoService.addBookInfo(isbn,bookName,author,publisher,category);
        return new ResultInfo(200);
    }



//    @RequestMapping("/addOrUpdate")
//    @ResponseBody
//    ResultInfo addUser(String userId, String userName, String userPassword, String phone, Integer identity){
//        userService.addUser(userId, userName, userPassword, phone, identity);
//        return new ResultInfo(200);
//    }

    //查询图书排行
//    @GetMapping("/rankList")
//    @ResponseBody
//    public Map<String,Object> queryBookRankListByParams(BookInfoQuery bookInfoQuery){
//        return bookInfoService.queryBookRankListByParams(bookInfoQuery);
//    }

}

