package com.nacos.storage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloud  StorageController
 * @description: 模拟仓库存储
 * @author: flchen
 * @create: 2021-04-23 00:42
 **/
@Slf4j
@RestController
public class StorageController {

    @GetMapping(value = "/sell")
    public String sellProduct(@RequestParam("productName")String productName, @RequestParam("num") Integer num){
        String message = productName + "nums - " + num;
        log.info(message);
        return message;
    }
}
