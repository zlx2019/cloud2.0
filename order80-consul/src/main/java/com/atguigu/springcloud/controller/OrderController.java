package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
    private static final String INVOKE_URL = "http://payment-service";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/consul")
    public String consul(){
        String result = restTemplate.getForObject(INVOKE_URL + "/paymentConsul",String.class);
        return result;
    }


}
