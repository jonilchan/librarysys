package com.gdufe.libsys.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jonil
 * @since 2021-12-03
 */
@Controller
@RequestMapping("/bookRank")
public class BookRankController {

    //图书排行榜
    @GetMapping("/rank")
    public String toRank(){
        return  "book/book_rank";
    }
}

