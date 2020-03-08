package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author L
 */
@RestController
public class PaymentController {

    @GetMapping("/payment/zk")
    public String paymentZk(){

        return "zookeeper" + UUID.randomUUID().toString();
    }
}
