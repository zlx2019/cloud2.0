package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.handler.CustomerBlockHandler;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    /**
     * blockHandler = "hanldeException"
     * 自定义的回调函数(在同类中存在,直接填写函数名即可)
     * @return
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "hanldeException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试",new Payment(2020L,"001"));
    }
    public CommonResult hanldeException(BlockException e){
        return new CommonResult(444,e.getClass().getCanonicalName() + "服务不可用");
    }

    //不指定blockHandler自定义回调函数,就会使用sentinel默认的错误提示信息
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource("byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按pathUrl限流",new Payment(4040l,"002"));
    }


    /**
     * 将兜底的函数抽取出 单独写一个类承载
     * @return
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class, //自定义回调函数的类
            blockHandler = "handlerException")  //函数名
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"按客户自定义Handler限流",new Payment(4040l,"003"));
    }

}
