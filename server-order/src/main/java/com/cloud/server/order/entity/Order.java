package com.cloud.server.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("`order`")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String buyer;

    private Integer productId;

    private String productName;

    private LocalDateTime createTime;

    private Integer status;

    private Integer isDelete;

    private LocalDateTime updateTime;

    private Integer num;


}
