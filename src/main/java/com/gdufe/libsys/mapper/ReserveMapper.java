package com.gdufe.libsys.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.libsys.base.BaseQuery;
import com.gdufe.libsys.entity.Borrow;
import com.gdufe.libsys.entity.Reserve;

import java.util.List;

public interface ReserveMapper extends BaseMapper<Reserve> {
    int deleteByPrimaryKey(Integer reserveId);

    int insert(Reserve record);

    int insertSelective(Reserve record);

    Reserve selectByPrimaryKey(Integer reserveId);

    int updateByPrimaryKeySelective(Reserve record);

    int updateByPrimaryKey(Reserve record);

    List<Reserve> selectByParams(BaseQuery baseQuery);
}