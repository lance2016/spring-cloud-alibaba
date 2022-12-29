package com.cloud.nacos.consumer.feign.fallback;

import com.cloud.nacos.common.param.ProductParam;
import com.cloud.nacos.consumer.feign.StorageService;
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
public class StorageFallBack implements StorageService {

    @Override
    public String sellProduct(ProductParam param) {
        String message = "检查库存服务挂了";
        log.info(message);
        return message;
    }
}
