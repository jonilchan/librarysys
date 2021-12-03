package com.gdufe.libsys.query;

import com.gdufe.libsys.base.BaseQuery;
import lombok.Data;


@Data
public class RankQuery extends BaseQuery {
    private Integer bookId;
    private String readerId;
    private String isbn;
}
