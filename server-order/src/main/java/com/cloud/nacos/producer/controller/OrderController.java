package com.cloud.nacos.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @program: springcloud  StorageController
 * @description: 模拟仓库存储
 * @author: flchen
 * @create: 2021-04-23 00:42
 **/
@Slf4j
@RestController
public class OrderController {

    @GetMapping("/order/{userName}/{productName}/{num}")
    public String order(@PathVariable("userName") String userName, @PathVariable("productName") String productName, @PathVariable("num") Integer num) {
        String message = LocalDateTime.now() + ", " + userName + " order " + "product" + productName + " " + num;
        log.info(message);
        return message;
    }
}
