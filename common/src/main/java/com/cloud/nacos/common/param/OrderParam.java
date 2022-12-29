package com.cloud.nacos.common.param;

import lombok.Data;

@Data
public class OrderParam {
    private String productName;
    private String buyer;
    private Integer num;
}
