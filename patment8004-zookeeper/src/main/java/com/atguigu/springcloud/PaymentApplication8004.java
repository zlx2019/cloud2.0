package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author L
 * 使用Zookeeper 作为cloud的注册中心
 */
@SpringBootApplication
@EnableDiscoveryClient //使用zookeeper或者consul作为注册中心所使用的注解
public class PaymentApplication8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8004.class,args);
    }
}
