package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.alibaba.pojo.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {

    //新建订单
    void saveOrder(Order order);

    //修改订单
    void update(Long userId,Integer status);
}
