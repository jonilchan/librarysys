package com.gdufe.libsys.service;

import com.gdufe.libsys.entity.BookStock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.libsys.query.BookStockQuery;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
public interface BookStockService extends IService<BookStock> {

    public Map<String, Object> selectAll(BookStockQuery bookStockQuery);

    public void addStock(String isbn,int bookAmount,int bookLocation);

    public void reduceStock(String isbn,int bookAmount,int bookLocation);

    public void deleBook(Integer bookId);

    public void transferToSS(Integer[] bookIds);

    public void transferToGZ(Integer[] bookIds);
}
