package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "commonErrorHandler")//默认的回调函数,没有指定特定的fallback函数的方法异常后都会执行这个函数
public class PaymentService {


    /**
     * ------------------服务降级
     */

    @HystrixCommand
    public String paymentInfo_ok(Integer id){
//        int i = 10 / 0;
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_ok" + id;
    }

    //单独指定 特定的回调函数
    @HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties = {
            //表示 超时时间为3秒,超过3秒将执行timeoutHandler方法
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_timeOut(Integer id){
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_timeOut" + id;
    }
    //特定的回调函数
    public String timeoutHandler(Integer id){
        return  "/payment/hystrix/timeout/"+ id + "---接口连接超时,请您稍后再试!";
    }

    //公共默认的错误回调函数
    public String commonErrorHandler(){
        return "服务器错误,请稍后再试!";
    }


    /**
     * ------------------服务熔断
     * 以下配置的效果:
     *      在10秒内,接到5次请求,如果有50%都请求全都失败,将开启熔断
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),//请求次数  默认为20
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期  默认10000
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),//失败率达到多少后熔断  默认 50%
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("id,不能为负数");
        }
        String num = IdUtil.simpleUUID();
        return "调用成功,流水号:" + num;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id，不能为复数"+ id;
    }

}
