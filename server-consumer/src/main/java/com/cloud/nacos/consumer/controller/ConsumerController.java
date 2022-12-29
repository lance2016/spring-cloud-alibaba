package com.cloud.nacos.consumer.controller;

import com.cloud.nacos.common.param.OrderParam;
import com.cloud.nacos.common.param.ProductParam;
import com.cloud.nacos.common.util.JsonUtil;
import com.cloud.nacos.consumer.feign.OrderService;
import com.cloud.nacos.consumer.feign.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @PostMapping("/buy")
    public Map<String, Object> buy(@RequestBody OrderParam param) {
        log.info("请求体:" + JsonUtil.toJson(param));
        Map<String, Object> retMap = new LinkedHashMap<>();
//        下单
        String orderMessage = orderService.order(param);
        retMap.put("order", orderMessage);
        log.info("订单系统消息 : " + orderMessage);
//        减库存
        ProductParam productParam = new ProductParam(param.getProductName(), param.getNum());
        String productMessage = storageService.sellProduct(productParam);
        retMap.put("product", productMessage);
        log.info("库存系统消息：" + productMessage);
//        扣款 TODO
        return retMap;
    }
}
