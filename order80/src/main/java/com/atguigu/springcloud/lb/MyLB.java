package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义负载均衡策略
 * 轮询算法
 *  当前访问的次数 % 可使用的服务节点数量 = 本次访问的服务节点索引
 */
@Component
public class MyLB implements LoadBalancer {

    /**
     * 访问的次数
     * 默认为 0
     */
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 获得该请求是第几次访问
     * @return
     */
    public final int getNextNodeServer(){
        int current;
        int next;//下个请求要访问的服务
        do {
            current = atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("第:" + next + "次访问");
        return next;
    }

    /**
     * 获取服务节点
     * @param serviceInstances
     * @return
     */
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getNextNodeServer() % serviceInstances.size();//取模,获取此次访问的服务节点索引
        ServiceInstance instance = serviceInstances.get(index);
        return instance;
    }
}
