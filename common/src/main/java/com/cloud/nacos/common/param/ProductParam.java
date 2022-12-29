package com.cloud.nacos.common.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author lance2.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductParam {
    private String productName;
    private Integer num;
}
