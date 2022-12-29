package com.cloud.nacos.consumer.feign;

import com.cloud.nacos.common.param.OrderParam;
import com.cloud.nacos.consumer.feign.fallback.OrderFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@FeignClient(value = "order-service", fallback = OrderFallBack.class)
public interface OrderService {

    @PostMapping("/order")
    String order(@RequestBody OrderParam orderParam);
}
