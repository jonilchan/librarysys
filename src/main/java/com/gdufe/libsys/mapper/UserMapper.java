package com.gdufe.libsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.libsys.base.BaseQuery;
import com.gdufe.libsys.entity.User;
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
public interface UserMapper extends BaseMapper<User> {
    //多条件查询
    List<User> selectByParams(BaseQuery baseQuery) throws DataAccessException;
}
