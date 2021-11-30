package com.gdufe.libsys.mapper;

import com.gdufe.libsys.base.BaseQuery;
import com.gdufe.libsys.entity.BookInfo;
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
public interface BookInfoMapper extends BaseMapper<BookInfo> {
    //多条件查询
    List<BookInfo> selectByParams(BaseQuery baseQuery) throws DataAccessException;
}
