package com.nacos.storage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.nacos.common.param.ProductParam;
import com.cloud.nacos.common.util.JsonUtil;
import com.nacos.storage.dao.ProductDao;
import com.nacos.storage.entity.Product;
import com.nacos.storage.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * (Product)表服务实现类
 *
 * @author makejava
 * @since 2022-12-30 00:23:46
 */
@Slf4j
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {

    @Resource
    Environment environment;
    @Resource
    ProductDao productDao;

    public String getPort() {
        return environment.getProperty("local.server.port");
    }

    @Override
    public String saveProduct(ProductParam param) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("name", param.getProductName());
        List<Product> productList = productDao.selectByMap(map);
        System.out.println(productList);
        if (productList.isEmpty()) {
            return String.format("%s: failed, %s not exist!", getPort(), param.getProductName());
        }
        Product product = productList.get(0);
        log.info(JsonUtil.toJson(product));
        if (Objects.nonNull(product)) {
            Integer realNum = product.getNum() - param.getNum();
            if (realNum < 0) {
                return "failed, num is not enough";
            } else {
                product.setNum(realNum);
                productDao.updateById(product);
            }
        }
        return String.format("%s: update %s success!", getPort(), product.getName());
    }

}

