package com.gdufe.libsys.service.impl;

import com.gdufe.libsys.entity.Category;
import com.gdufe.libsys.mapper.CategoryMapper;
import com.gdufe.libsys.service.CategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
