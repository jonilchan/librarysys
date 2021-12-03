package com.gdufe.libsys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jonil
 * @since 2021-12-03
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class BookRank implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 书Id
     */
        private Integer bookId;

      /**
     * 读者Id
     */
      private String readerId;

      /**
     * isbn
     */
      private String isbn;

      /**
     * 借书时间
     */
      private LocalDateTime borrowTime;


}
