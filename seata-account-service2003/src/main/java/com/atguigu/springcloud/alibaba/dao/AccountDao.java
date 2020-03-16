package com.atguigu.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {

    //金额扣减
    void decrease(@Param("userId")Long userId, @Param("money")BigDecimal money);
}
