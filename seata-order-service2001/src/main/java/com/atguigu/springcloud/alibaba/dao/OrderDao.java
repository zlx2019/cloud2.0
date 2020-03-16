package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    //新建订单
    void create(Order order);

    //修改订单
    void update(@Param("userId") Long userId,@Param("status")Integer status);
}
