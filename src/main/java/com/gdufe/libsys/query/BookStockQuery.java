package com.gdufe.libsys.query;

import com.gdufe.libsys.base.BaseQuery;
import lombok.Data;

@Data
public class BookStockQuery extends BaseQuery {
    //姓名
    private String isbn;

    private Integer status;

    private Integer bookLocation;
}
