package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    public static final String PAYMENT_URL = "http://localhost:8001";

    //调用支付服务,添加流水号
    @GetMapping("/consumer/payment/save")
    public CommonResult<Payment> save(Payment payment){
        CommonResult result = restTemplate.postForObject(PAYMENT_URL + "/payment/save", payment, CommonResult.class);
        return result;
    }

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        return  restTemplate.getForObject(PAYMENT_URL + "/payment/"+ id,CommonResult.class);
    }

}
