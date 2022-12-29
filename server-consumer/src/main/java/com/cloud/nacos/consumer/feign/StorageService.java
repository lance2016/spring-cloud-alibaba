package com.cloud.nacos.consumer.feign;

import com.cloud.nacos.common.param.ProductParam;
import com.cloud.nacos.consumer.feign.fallback.StorageFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(value = "storage-service", fallback = StorageFallBack.class)
public interface StorageService {

    @PostMapping("/sell")
    String sellProduct(@RequestBody ProductParam param);
}
