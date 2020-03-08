package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {

    @GetMapping("/paymentConsul")
    public String paymentConsul(){
        return "Consul:" + UUID.randomUUID().toString();
    }
}
