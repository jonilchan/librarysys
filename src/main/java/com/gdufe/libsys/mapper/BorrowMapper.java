package com.gdufe.libsys.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.libsys.base.BaseQuery;
import com.gdufe.libsys.entity.Borrow;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BorrowMapper extends BaseMapper<Borrow> {
    //多条件查询
    List<Borrow> selectByParams(BaseQuery baseQuery) throws DataAccessException;


}