package com.atguigu.springcloud.alibaba.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {


    private Long id;
    private Long userId;
    private Long productId;//商品id
    private Integer count;//数量
    private BigDecimal money;//金额
    private Integer status;//订单状态0:创建中, 1:已完结
}
