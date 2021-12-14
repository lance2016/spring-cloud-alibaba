package com.cloud.nacos.consumer.feign;

import com.cloud.nacos.consumer.feign.fallback.StorageFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "storage-service", fallback = StorageFallBack.class)
public interface StorageService {

    @GetMapping("sell")
    public String sellProduct(@RequestParam("productName")String productName, @RequestParam("num") Integer num);
}
