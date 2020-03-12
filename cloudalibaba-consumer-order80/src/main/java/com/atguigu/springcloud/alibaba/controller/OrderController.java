package com.atguigu.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    @Value("${service-url.payment-service}")
    private String paymentUrl;

    @Autowired
    private RestTemplate restTemplate;

    //调用payment服务
    @GetMapping("/nacos/consumer/payment/{id}")
    public String toPayment(@PathVariable Integer id){
        String result = restTemplate.getForObject(paymentUrl + "/payment/nacos/" + id, String.class);
        return result;
    }
}
