package com.nacos.storage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.nacos.common.param.ProductParam;
import com.nacos.storage.entity.Product;

/**
 * (Product)表服务接口
 *
 * @author makejava
 * @since 2022-12-30 00:23:45
 */
public interface ProductService extends IService<Product> {
    String saveProduct(ProductParam param);
}

