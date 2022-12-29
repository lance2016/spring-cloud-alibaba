package com.nacos.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan( "com.nacos.storage.dao")
public class ServerStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerStorageApplication.class, args);
    }

}
