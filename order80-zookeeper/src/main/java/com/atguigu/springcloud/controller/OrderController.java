package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    private static final String PAYMENT_URL = "http://payment-service";

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/paymentZk")
    public String order(){
        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/zk",String.class);
        return result;
    }
}
