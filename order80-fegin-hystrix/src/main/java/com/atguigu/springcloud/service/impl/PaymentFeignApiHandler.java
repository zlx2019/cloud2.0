package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentFeignApi;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignApiHandler implements PaymentFeignApi {
    @Override
    public String ok(Integer id) {
        return "ok 服务内部错误,请稍后再试!";
    }

    @Override
    public String timeout(Integer id) {
        return "不好意思,请求数据超时,可能数据量过大,请稍后再试!";
    }

    //公共的错误回调函数
    public String CommonErrorHandler(){
        return "调用服务失败,请联系管理员!";
    }
}
