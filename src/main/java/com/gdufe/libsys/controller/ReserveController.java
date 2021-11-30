package com.gdufe.libsys.controller;


import com.gdufe.libsys.base.BaseController;
import com.gdufe.libsys.query.BookStockQuery;
import com.gdufe.libsys.query.BorrowQuery;
import com.gdufe.libsys.query.ReserveQuery;
import com.gdufe.libsys.service.BookStockService;
import com.gdufe.libsys.service.BorrowService;
import com.gdufe.libsys.service.ReserveService;
import com.gdufe.libsys.utils.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/reserve")
public class ReserveController extends BaseController {

    @Resource
    private ReserveService reserveService;

    @Resource
    BookStockService bookStockService;

    String isbn;
    String readerId;
    Integer reserveId;


    //跳转到预约表
    @RequestMapping("/toReserve")
    public String toReserve(){
        return "/reserve/reserve_list";
    }

    //加载预约列表
    @GetMapping("/reserveList")
    @ResponseBody
    public Map<String,Object> queryReserveListByParams(ReserveQuery reserveQuery){
        Map<String, Object> stringObjectMap = reserveService.queryReserveListByParams(reserveQuery);
        return stringObjectMap;
    }


    //加载库存表
    @GetMapping("/toStock")
    public String toStock(String isbn, String readerId,Integer reserveId,HttpServletRequest request){
        request.setAttribute("isbn",isbn);
//        request.setAttribute("readerId",readerId);
//        request.setAttribute("reserveId",reserveId);
        this.isbn = isbn;
        this.readerId = readerId;
        this.reserveId = reserveId;
        return "reserve/reserve_process";
    }

    //查询该isbn下的图书库存(处理预约按钮对应的
    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> queryBookStocksByParams(BookStockQuery bookStockQuery) {
        bookStockQuery.setIsbn(isbn);
        bookStockQuery.setStatus(10);
        return bookStockService.selectAll(bookStockQuery);
    }

    //归还图书
    @PostMapping ("/selectBook")
    @ResponseBody
    public ResultInfo selectBookById(Integer bookId) {
         return reserveService.selectBookById(bookId,readerId,reserveId);
    }
}

