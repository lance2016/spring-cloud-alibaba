package com.cloud.server.order.controller;

import com.cloud.nacos.common.param.OrderParam;
import com.cloud.server.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    OrderService orderService;



    @PostMapping("/order")
    public String order(@RequestBody OrderParam param) {
       return orderService.saveOrder(param);
    }
}
