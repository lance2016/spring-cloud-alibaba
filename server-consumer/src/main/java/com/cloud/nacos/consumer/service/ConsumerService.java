package com.cloud.nacos.consumer.service;

import java.io.IOException;

public interface ConsumerService {
    void checkZk(String path) throws Exception;

    void createDocument() throws IOException;
}
