package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("payment-service")
public interface PaymentFeignApi {

    @GetMapping("/payment/{id}")
    CommonResult<Payment> getById(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    public String timeout();

    @GetMapping("/payment/zipkin")
    public String zk();
}
