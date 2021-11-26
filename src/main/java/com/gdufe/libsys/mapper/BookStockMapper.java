package com.gdufe.libsys.mapper;

import com.gdufe.libsys.entity.BookInfo;
import com.gdufe.libsys.entity.BookStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
public interface BookStockMapper extends BaseMapper<BookStock> {
    int insert(BookInfo record);
}
