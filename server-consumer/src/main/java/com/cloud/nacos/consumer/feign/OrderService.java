package com.cloud.nacos.consumer.feign;

import com.cloud.nacos.consumer.feign.fallback.OrderFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Service
@FeignClient(value = "order-service", fallback = OrderFallBack.class)
public interface OrderService {

    @GetMapping("/order/{userName}/{productName}/{num}")
    String order(@PathVariable("userName") String userName, @PathVariable("productName") String productName, @PathVariable("num") Integer num);
}
