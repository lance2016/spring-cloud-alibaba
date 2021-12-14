package com.cloud.nacos.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: springcloud  TestBean
 * @description:
 * @author: flchen
 * @create: 2021-05-01 09:40
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestBean {
    private String username;
    private String password;
}
