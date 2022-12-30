package com.cloud.nacos.consumer.service.impl;

import com.cloud.nacos.consumer.service.ConsumerService;
import com.cloud.nacos.consumer.util.ZkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Resource
    ZkUtils zkUtils;

    @Override
    public void checkZk(String path) throws Exception {
        Stat stat = zkUtils.isExists(path);
        if (null == stat) {
            log.info("node not exist");
            String node = zkUtils.createNode(path, "comsumer", CreateMode.EPHEMERAL);
            if (node == null) {
                log.error("create node failed");
            } else {
                log.info("created node");
            }
        } else {
            log.info("node exist");
        }

    }
}
