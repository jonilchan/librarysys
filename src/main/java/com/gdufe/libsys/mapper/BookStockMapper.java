package com.gdufe.libsys.mapper;

import com.gdufe.libsys.base.BaseQuery;
import com.gdufe.libsys.entity.BookInfo;
import com.gdufe.libsys.entity.BookStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
public interface BookStockMapper extends BaseMapper<BookStock> {

    int deleteByPrimaryKey(Integer bookId);

    int insert(BookStock record);

    int insertSelective(BookStock record);

    BookStock selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(BookStock record);

    int updateByPrimaryKey(BookStock record);

    //多条件查询
    List<BookStock> selectAll(BaseQuery baseQuery) throws DataAccessException;


}
