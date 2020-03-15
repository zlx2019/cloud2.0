package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.alibaba.service.handler.PaymentFeignApiHandler;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-service",fallback = PaymentFeignApiHandler.class)
public interface PaymentFeignApi {

    //调用payment9003 or 9004
    @GetMapping("/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
