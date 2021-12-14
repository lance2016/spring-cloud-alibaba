package com.cloud.nacos.consumer.controller;

import com.cloud.nacos.consumer.feign.OrderService;
import com.cloud.nacos.consumer.feign.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author lance
 */
@RestController
public class ConsumerController {

    @Resource
    private OrderService orderService;
    @Resource
    private StorageService storageService;


    @GetMapping("/buy/{userName}/{productName}/{num}")
    public String buy(@PathVariable("userName") String userName, @PathVariable("productName") String productName, @PathVariable("num") Integer num) {
        System.out.println("123");
        orderService.order(userName, productName, num);
        storageService.sellProduct(productName, num);
        return "success";
    }
}
