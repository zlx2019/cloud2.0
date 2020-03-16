package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.pojo.CommonResult;
import com.atguigu.springcloud.alibaba.pojo.Order;
import com.atguigu.springcloud.alibaba.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //下订单
    @PostMapping
    public CommonResult saveOrder(@RequestBody Order order){
        orderService.saveOrder(order);
        return new CommonResult(200,"添加订单成功");
    }

    //修改订单状态
    @PutMapping
    public CommonResult updateOrderStatus(Long id,Integer status){
        orderService.update(id,status);
        return new CommonResult(200,"修改成功");
    }


}
