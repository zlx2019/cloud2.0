package com.atguigu.springcloud.alibaba.service.handler;

import com.atguigu.springcloud.alibaba.service.PaymentFeignApi;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignApiHandler implements PaymentFeignApi {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"调用paymentSQL失败,服务降级!",new Payment(id,"error"));
    }
}
