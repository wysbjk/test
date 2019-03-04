package com.turbid.dataserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching //开启缓存
public class DataServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataServerApplication.class, args);
    }
}
