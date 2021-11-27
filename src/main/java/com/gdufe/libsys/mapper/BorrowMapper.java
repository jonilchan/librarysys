package com.gdufe.libsys.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.libsys.base.BaseQuery;
import com.gdufe.libsys.entity.BookInfo;
import com.gdufe.libsys.entity.Borrow;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BorrowMapper extends BaseMapper<Borrow> {
    int deleteByPrimaryKey(Integer borrowId);

    int insert(Borrow record);

    int insertSelective(Borrow record);

    Borrow selectByPrimaryKey(Integer borrowId);

    int updateByPrimaryKeySelective(Borrow record);

    int updateByPrimaryKey(Borrow record);

    //多条件查询
    List<Borrow> selectByParams(BaseQuery baseQuery) throws DataAccessException;
}