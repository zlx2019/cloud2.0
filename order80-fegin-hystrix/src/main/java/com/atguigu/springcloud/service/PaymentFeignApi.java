package com.atguigu.springcloud.service;

import com.atguigu.springcloud.service.impl.PaymentFeignApiHandler;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "payment-hystrix-com.atguigu.springcloud.service",fallback = PaymentFeignApiHandler.class)
public interface PaymentFeignApi {

    @GetMapping("/payment/hystrix/ok/{id}")
    String ok(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id") Integer id);

}
