package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.service.PaymentFeignApi;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * nacos + sentinel + feign 调用支付微服务
 */

@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignApi paymentFeignApi;

    @GetMapping("/consumer/feign/fallback/{id}")
    public CommonResult<Payment> fallback(@PathVariable Long id){
        CommonResult<Payment> result = paymentFeignApi.paymentSQL(id);
        if (id == 4){
            throw new IllegalArgumentException("非法参数异常....");
        }else if (result.getData() == null){
            throw new NullPointerException("没有对应的记录,空指针异常....");
        }
        return result;
    }

}
