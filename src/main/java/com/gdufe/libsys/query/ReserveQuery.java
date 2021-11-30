package com.gdufe.libsys.query;


import com.gdufe.libsys.base.BaseQuery;
import lombok.Data;

@Data
public class ReserveQuery extends BaseQuery {
    private Integer reserveId;
    private Integer bookId;
    private String readerId;
    private String isbn;
    private Integer readerIdentity;
}
