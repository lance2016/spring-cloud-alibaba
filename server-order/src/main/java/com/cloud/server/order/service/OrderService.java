package com.cloud.server.order.service;

import com.cloud.nacos.common.param.OrderParam;
import com.cloud.server.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-12-29
 */
public interface OrderService extends IService<Order> {
    String saveOrder(OrderParam orderParam);
}
