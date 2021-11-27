package com.gdufe.libsys.service.impl;

import com.gdufe.libsys.entity.BookInfo;
import com.gdufe.libsys.mapper.BookInfoMapper;
import com.gdufe.libsys.query.BookInfoQuery;
import com.gdufe.libsys.service.BookInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements BookInfoService {

    @Autowired
    BookInfoMapper bookInfoMapper;


    //查询图书列表
    public Map<String, Object> queryBookInfosByParams(BookInfoQuery bookInfoQuery) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageHelper.startPage(bookInfoQuery.getPage(),bookInfoQuery.getLimit());
        PageInfo<BookInfo> pageInfo = new PageInfo<>(bookInfoMapper.selectByParams(bookInfoQuery));
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
}
