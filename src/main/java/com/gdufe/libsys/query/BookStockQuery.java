package com.gdufe.libsys.query;

import com.gdufe.libsys.base.BaseQuery;
import lombok.Data;

@Data
public class BookStockQuery extends BaseQuery {
    //isbn
    private String isbn;
    //图书状态
    private Integer status;
    //馆藏位置
    private Integer bookLocation;

    private Integer bookId;
}
