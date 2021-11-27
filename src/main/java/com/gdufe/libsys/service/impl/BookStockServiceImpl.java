package com.gdufe.libsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.libsys.entity.BookStock;
import com.gdufe.libsys.mapper.BookStockMapper;
import com.gdufe.libsys.query.BookStockQuery;
import com.gdufe.libsys.service.BookStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
@Service
public class BookStockServiceImpl extends ServiceImpl<BookStockMapper, BookStock> implements BookStockService {

    @Resource
    private BookStockMapper bookStockMapper;

    @Override
    public Map<String, Object> selectAll(BookStockQuery bookStockQuery) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageHelper.startPage(bookStockQuery.getPage(), bookStockQuery.getLimit());
//         QueryWrapper<BookStock> wrapper = new QueryWrapper<>();
        PageInfo<BookStock> pageInfo = new PageInfo<BookStock>(bookStockMapper.selectAll(bookStockQuery));
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
}
