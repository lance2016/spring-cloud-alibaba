package com.cloud.server.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: springcloud  StorageController
 * @description: 模拟仓库存储
 * @author: flchen
 * @create: 2021-04-23 00:42
 **/
@Slf4j
@RestController
public class OrderController {

    @Resource
    Environment environment;

    public String getPort() {
        return environment.getProperty("local.server.port");
    }

    @GetMapping("/order/{userName}/{productName}/{num}")
    public String order(@PathVariable("userName") String userName, @PathVariable("productName") String productName, @PathVariable("num") Integer num) {
        System.out.println(getPort());
        String message = String.format("port:%s, %s order product %s, number:%d", getPort(), userName, productName, num);
        log.info(message);
        return message;
    }
}
