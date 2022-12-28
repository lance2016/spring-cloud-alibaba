package com.cloud.nacos.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.cloud.nacos.property.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: spring-cloud-demo  ConfigController
 * @description:
 * @author: flchen
 * @create: 2021-04-20 19:38
 **/
@Slf4j
@RestController
@RequestMapping("config")

public class ConfigController {

    @Resource
    AppConfig appConfig;


    @NacosValue(value = "${app-name:}", autoRefreshed = true)
    private String appName;

    @NacosValue(value = "${app.test:}", autoRefreshed = true)
    private String testVal;

    @GetMapping(value = "/get")
    @ResponseBody
    public String get() {
        String ret = String.format("app-config:%s,%s\napp-name:%s\napp-test:%s", appConfig.getTest(), appConfig.getTest2(), appName, testVal);
        log.info(ret);
        return ret;
    }


    @GetMapping("")
    public void test() {

    }

}