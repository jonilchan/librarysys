package com.gdufe.libsys.query;


import com.gdufe.libsys.base.BaseQuery;
import lombok.Data;

@Data
public class BookInfoQuery extends BaseQuery {
    //姓名
    private String isbn;
    //邮箱
    private String bookName;
    //电话
    private String author;
}
