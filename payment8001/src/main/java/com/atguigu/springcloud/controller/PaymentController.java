package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment/{id}")
    public CommonResult getById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null){
            return new CommonResult(200,"查询成功",payment);
        }
        return new CommonResult(500,"查询失败",null);
    }

    @PostMapping("/payment/save")
    public CommonResult save(@RequestBody Payment payment){
        int result = paymentService.save(payment);
        log.info("-------响应结果:"+ result);
        if (result > 0){
            return new CommonResult(200,"添加成功",result);
        }
        return new CommonResult(500,"失败");
    }
}
