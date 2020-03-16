package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.OrderDao;
import com.atguigu.springcloud.alibaba.feign.AccountApi;
import com.atguigu.springcloud.alibaba.feign.StorageApi;
import com.atguigu.springcloud.alibaba.pojo.Order;
import com.atguigu.springcloud.alibaba.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StorageApi storageApi;
    @Autowired
    private AccountApi accountApi;


    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class) //添加全局事务
    public void saveOrder(Order order) {
        log.info("开始创建订单....");
        order.setStatus(0);//默认0
        orderDao.create(order);
        log.info("调用库存服务,扣减库存.....");
        storageApi.decrease(order.getProductId(),order.getCount());
        log.info("库存扣减完成~~~");

        log.info("调用账户服务,扣减金额.....");
        accountApi.decrease(order.getUserId(),order.getMoney());
        log.info("金额扣减完成~~~");

        log.info("修改订单状态.....");
        orderDao.update(order.getUserId(),0);
        log.info("订单修改完成~~~");

        log.info("订单结束!");
    }

    @GlobalTransactional(name = "fsp-update-order",rollbackFor = Exception.class)
    @Override
    public void update(Long userId, Integer status) {
        orderDao.update(userId, status);
        System.out.println("修改完成...");
    }

}
