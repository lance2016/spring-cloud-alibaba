package com.cloud.server.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServerOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerOrderApplication.class, args);
	}
}
