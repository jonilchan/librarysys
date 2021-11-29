package com.gdufe.libsys.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BookRankVo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * isbn
     */
    @TableId(value = "isbn")
    private String isbn;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 总库存
     */
    private Integer totalStock;

    /**
     * 当前库存
     */
    private Integer borrowTimesInThreeMonths;

    /**
     * 0正常，1暂停借阅
     */
    private Integer status;

    /**
     * 0三水，1广州，2三水广州
     */
    private Integer bookLocation;

    /**
     * 类别号
     */
    private Integer categoryId;

    /**
     * 入库时间
     */
    private float borrowStockRatio;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
