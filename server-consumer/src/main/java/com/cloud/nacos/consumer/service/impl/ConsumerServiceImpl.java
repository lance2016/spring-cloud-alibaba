package com.cloud.nacos.consumer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cloud.nacos.consumer.entity.ItemEntity;
import com.cloud.nacos.consumer.service.ConsumerService;
import com.cloud.nacos.consumer.util.EsUtil;
import com.cloud.nacos.consumer.util.ZkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Resource
    ZkUtils zkUtils;
    @Resource
    private EsUtil esUtil;

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

    @Override
    public void createDocument() throws IOException {
        String index = "item_index";
        String id = "10001";
        if (esUtil.existsById(index, id)) {
            log.error("es document exist");
            return;
        }
        ItemEntity entity = new ItemEntity();
        entity.setId(Long.parseLong(id));
        entity.setCategory("no");
        entity.setBrand("b");
        entity.setPrice(19D);
        entity.setImages("www.baoid.com");
        esUtil.addData((JSONObject) JSONObject.toJSON(entity), index, id);
    }
}
