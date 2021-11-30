package com.gdufe.libsys.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ReserveVo implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer reserveId;

    private Integer readerIdentity;

    private Date reserveTime;
    //预约状态
    private Integer status;

    private String isbn;

    private String bookName;

    private Integer presentStock;

    private Integer bookLocation;

    private Integer bookId;

    private String readerId;



}
