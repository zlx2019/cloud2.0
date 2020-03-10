package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    PaymentFeignApi paymentFeignApi;


    @GetMapping("/payment/consumer/hystrix/ok/{id}")
    public String ok(@PathVariable Integer id){
        String result = paymentFeignApi.ok(id);
        log.info(result);
        return result;
    }

    @GetMapping("/payment/consumer/hystrix/timeout/{id}")
    public String timeout(@PathVariable Integer id){
        String result = paymentFeignApi.timeout(id);
        log.info(result);
        return result;
    }

}
