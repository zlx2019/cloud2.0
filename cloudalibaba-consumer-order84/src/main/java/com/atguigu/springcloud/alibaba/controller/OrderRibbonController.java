package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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
 * nacos + sentinel + ribbon 调用支付微服务
 */
@RestController
@Slf4j
public class OrderRibbonController {

    @Value("${server-url.payment-service}")
    private String PAYMENT_SERVICE;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/ribbon/fallback/{id}")
    //@SentinelResource(value = "fallback")//没配置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback")//fakkback 负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "handlerHandler")//blockHandler负责sentinel控制台配置违规
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "handlerHandler")//同时都配置,如果同时触发两者,blockHandler优先级高于fallback
    public CommonResult<Payment> fallback(@PathVariable Long id){
        CommonResult<Payment> result = restTemplate.getForObject(PAYMENT_SERVICE + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4){
            throw new IllegalArgumentException("非法参数异常....");
        }else if (result.getData() == null){
            throw new NullPointerException("没有对应的记录,空指针异常....");
        }
        return result;
    }
    //异常回调函数
    public CommonResult handlerFallback(@PathVariable Long id,Throwable e){
        Payment payment = new Payment(id, "null");
        return new CommonResult(444,"发现错误,执行fallback函数,错误信息:" + e.getMessage(),payment);
    }

    //限流or降级等规则违规 回调函数
    public CommonResult handlerHandler(@PathVariable Long id, BlockException e){
        Payment payment = new Payment(id, "null");
        return new CommonResult(444,"目前系统不可用,sentinel限流生效~~ 错误信息:" + e.getMessage(),payment);
    }

}
