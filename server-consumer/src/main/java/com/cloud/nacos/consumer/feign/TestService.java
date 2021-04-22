package com.cloud.nacos.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Service
@FeignClient(value = "alibaba-nacos-discovery-server")
public interface TestService {

    @GetMapping("/hello")
    public String test(@RequestParam("name") String name);
}
