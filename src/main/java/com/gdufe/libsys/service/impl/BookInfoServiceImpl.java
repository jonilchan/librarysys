package com.gdufe.libsys.service.impl;

import com.gdufe.libsys.entity.BookInfo;
import com.gdufe.libsys.mapper.BookInfoMapper;
import com.gdufe.libsys.service.BookInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
