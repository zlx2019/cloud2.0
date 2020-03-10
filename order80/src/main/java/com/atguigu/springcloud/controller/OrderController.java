package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://PAYMENT-SERVICE";


    //调用支付服务,添加流水号
    @GetMapping("/consumer/payment/save")
    public CommonResult<Payment> save(Payment payment){
        CommonResult result = restTemplate.postForObject(PAYMENT_URL + "/payment/save", payment, CommonResult.class);
        return result;
    }

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        return  restTemplate.getForObject(PAYMENT_URL + "/payment/"+ id,CommonResult.class);
    }

    /**
     * 使用自定义的负载均衡接口
     */
    @Autowired
    private LoadBalancer loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/lb")
    public String getOrderLb(){
        List<ServiceInstance> instanceList = discoveryClient.getInstances("PAYMENT-SERVICE");//获取所有可用服务节点
        if (instanceList.isEmpty()){
            return null;
        }
        ServiceInstance instance = loadBalancer.instance(instanceList);
        URI uri = instance.getUri();
        String serverPort = restTemplate.getForObject(uri + "/payment/lb", String.class);
        return serverPort;
    }

}
