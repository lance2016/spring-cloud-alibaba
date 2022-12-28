package com.cloud.nacos.consumer.controller;

import com.cloud.nacos.consumer.feign.OrderService;
import com.cloud.nacos.consumer.feign.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author lance
 */
@Slf4j
@RestController
public class ConsumerController {

    @Resource
    private OrderService orderService;
    @Resource
    private StorageService storageService;


    @GetMapping("/buy/{userName}/{productName}/{num}")
    public Map<String,Object> buy(@PathVariable("userName") String userName, @PathVariable("productName") String productName, @PathVariable("num") Integer num) {
        Map<String, Object> retMap = new LinkedHashMap<>();
//        下单
        String orderMessage = orderService.order(userName, productName, num);
        retMap.put("order", orderMessage);
        log.info("订单系统消息 : "+orderMessage);
//        减库存
        String productMessage = storageService.sellProduct(productName, num);
        retMap.put("product", productMessage);
        log.info("库存系统消息："+ productMessage);
//        扣款 TODO
        return retMap;
    }
}
