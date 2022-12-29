package com.nacos.storage.controller;

import com.cloud.nacos.common.param.ProductParam;
import com.nacos.storage.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: springcloud  StorageController
 * @description: 模拟仓库存储
 * @author: flchen
 * @create: 2021-04-23 00:42
 **/
@RestController
public class StorageController {

    @Resource
    ProductService productService;

    @PostMapping(value = "/sell")
    public String sellProduct(@RequestBody ProductParam param) {
        return productService.saveProduct(param);
    }
}
