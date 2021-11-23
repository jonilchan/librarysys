package com.gdufe.libsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class Category implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 类别id
     */
        @TableId(value = "category_id", type = IdType.AUTO)
      private Integer categoryId;

      /**
     * 类别名
     */
      private String categoryName;


}
