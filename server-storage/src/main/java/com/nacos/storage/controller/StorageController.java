package com.nacos.storage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class StorageController {

    @Resource
    Environment environment;

    public String getPort() {
        return environment.getProperty("local.server.port");
    }

    @GetMapping(value = "/sell")
    public String sellProduct(@RequestParam("productName") String productName, @RequestParam("num") Integer num) {
        String message = String.format("port:%s, %s nums - %d", getPort(), productName, num);
        log.info(message);
        return message;
    }
}
