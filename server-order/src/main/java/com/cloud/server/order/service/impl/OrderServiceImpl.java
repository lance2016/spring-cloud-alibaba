package com.cloud.server.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.nacos.common.param.OrderParam;
import com.cloud.nacos.common.util.JsonUtil;
import com.cloud.server.order.entity.Order;
import com.cloud.server.order.mapper.OrderMapper;
import com.cloud.server.order.property.AppProperty;
import com.cloud.server.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-12-29
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private AppProperty appProperty;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private Environment environment;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public String getPort() {
        return environment.getProperty("local.server.port");
    }

    /**
     * 具体业务逻辑在这里写，不要在controller里面
     */
    @Override
    public String saveOrder(OrderParam param) {
        log.info(JsonUtil.toJson(param));
        String productName = appProperty.getProductName();
        Integer maxNum = appProperty.getMaxNum();
        log.info(productName+":"+maxNum);
        Order order = new Order();
        order.setBuyer(param.getBuyer());
        order.setProductName(param.getProductName());
        order.setNum(param.getNum());
        redisTemplate.opsForValue().set("order", order);
        orderMapper.insert(order);
        String message = String.format("%s:save success",getPort());
        log.info(message);
        return message;
    }
}
