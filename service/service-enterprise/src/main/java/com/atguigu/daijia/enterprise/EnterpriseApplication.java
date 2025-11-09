package com.atguigu.daijia.enterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * EnterpriseApplication
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.atguigu.daijia")
@ComponentScan(basePackages = {"com.atguigu.daijia"})
public class EnterpriseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnterpriseApplication.class, args);
    }
}
