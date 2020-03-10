package com.atguigu.springcloud;

import com.atguigu.ribbon.myrule.MySelRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
//name = 要访问都服务,configuration = 指定的负载均衡策略
//@RibbonClient(name = "PAYMENT-SERVICE",configuration = MySelRule.class)
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }

    @Bean
    //@LoadBalanced //开启负载均衡策略
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
