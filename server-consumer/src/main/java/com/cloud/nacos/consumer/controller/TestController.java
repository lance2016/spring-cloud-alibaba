package com.cloud.nacos.consumer.controller;

import com.cloud.nacos.consumer.feign.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;
    @Resource
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return testService.test(name);
    }


    @GetMapping("/test")
    public String test() {

        /**
         *   通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
         *   这里使用了Spring Cloud Common中的LoadBalancerClient接口来挑选服务实例信息。
         *  然后从挑选出的实例信息中获取可访问的URI，拼接上服务提供方的接口规则来发起调用。
         */
        ServiceInstance serviceInstance = loadBalancerClient.choose("alibaba-nacos-discovery-server");
        String url = serviceInstance.getUri() + "/hello?name=" + "didi";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return "Invoke : " + url + ", return : " + result;
    }

}
