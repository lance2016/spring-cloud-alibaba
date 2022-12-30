package com.nacos.storage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.nacos.common.param.ProductParam;
import com.cloud.nacos.common.util.JsonUtil;
import com.nacos.storage.dao.ProductDao;
import com.nacos.storage.entity.Product;
import com.nacos.storage.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
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

    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    public String getPort() {
        return environment.getProperty("local.server.port");
    }


    @Override
    public String saveProduct(ProductParam param) {
//        先查询redis中是否存在
        Product product = (Product) redisTemplate.opsForValue().get(param.getProductName());
        if (Objects.nonNull(product)) {
            log.info("exist in redis");
        }
//        查询数据库
        else {
            Map<String, Object> map = new HashMap<>(2);
            map.put("name", param.getProductName());
            List<Product> productList = productDao.selectByMap(map);
            if (productList.isEmpty()) {
                return String.format("%s: failed, %s not exist!", getPort(), param.getProductName());
            }
            product = productList.get(0);
        }
//        计算剩余数量是否足够
        Integer realNum = product.getNum() - param.getNum();
        if (realNum < 0) {
            return "failed, num is not enough";
        } else {
            product.setNum(realNum);
        }
        log.info(String.format("product: %s", JsonUtil.toJson(product)));
//        保存到redis
        log.info("save to redis");
        redisTemplate.opsForValue().set(param.getProductName(), product);
        log.info(JsonUtil.toJson(product));
        productDao.updateById(product);

        return String.format("%s: update %s success, rest num: %d", getPort(), product.getName(), realNum);
    }

}

