package com.cloud.nacos.consumer.feign.fallback;

import com.cloud.nacos.common.param.OrderParam;
import com.cloud.nacos.consumer.feign.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: springcloud  TestFallBack
 * @description:
 * @author: flchen
 * @create: 2021-04-22 23:34
 **/

@Slf4j
@Component
public class OrderFallBack implements OrderService {


    @Override
    public String order(OrderParam orderParam) {
        log.info("下单服务挂了");
        return "服务暂时已下线";
    }
}
