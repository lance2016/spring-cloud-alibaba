package com.cloud.nacos.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @program: zk-demo  ZkCoreClient
 * @description:
 * @author: flchen
 * @create: 2022-07-06 14:27
 **/

@Slf4j
@Configuration
public class ZkConfig {
    /**
     * zk 服务端集群地址
     */
    @Value("${zk.url}")
    private String zkUrl;

    /**
     * session 超时时间
     */
    private int timeOut = 60000;

    /**
     * zkclient 重试间隔时间
     */
    private int baseSleepTimeMs = 5000;

    /**
     * zkclient 重试次数
     */
    private int retryCount = 5;


    /**
     * 使用double-check 创建client
     *
     * @return
     */
    @Bean
    public CuratorFramework init() {
        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString(zkUrl)
                .sessionTimeoutMs(timeOut)
                .retryPolicy(new ExponentialBackoffRetry(baseSleepTimeMs, retryCount))
                .build();
        client.start();
        log.info("client is created at ================== {}", LocalDateTime.now());

        return client;
    }

}
