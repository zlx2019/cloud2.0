package com.atguigu.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

/**
 * 自定义回调handler
 */
public class CustomerBlockHandler {

    //全局回调函数
    public static CommonResult handlerException(BlockException e){
        return new CommonResult(4444,"系统暂时不可用~~~");
    }
}
