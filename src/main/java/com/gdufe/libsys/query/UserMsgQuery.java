package com.gdufe.libsys.query;

import com.gdufe.libsys.base.BaseQuery;
import lombok.Data;

@Data
public class UserMsgQuery extends BaseQuery {
    /**
     * 用户ID
     */
    private String userId;
}
