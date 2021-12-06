package com.gdufe.libsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.libsys.base.BaseQuery;
import com.gdufe.libsys.entity.BookStock;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
public interface BookStockMapper extends BaseMapper<BookStock> {
    //多条件查询
    List<BookStock> selectAll(BaseQuery baseQuery) throws DataAccessException;


}
