package com.turbid.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

import java.nio.charset.Charset;
import java.util.Base64;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    @Bean
    public HttpHeaders getHeader() {
        HttpHeaders headers=new HttpHeaders();
        String auth="user:qazwsx321";//认证的原始信息
        byte[] encodeAuth=Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));//将原始认证信息进行Base64加密
        String authHeader="Basic "+new String(encodeAuth);//加密后的认证信息要与Basic有个空格
        headers.set("Authorization", authHeader);
        return headers;
    }


    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
