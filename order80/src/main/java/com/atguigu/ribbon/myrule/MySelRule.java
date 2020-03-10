package com.atguigu.ribbon.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author L
 * 自定义Ribbon 负载均衡策略
 */
@Configuration
public class MySelRule {

    @Bean
    public IRule randomRule(){
        return new RandomRule();//定义为随机策略
    }
}
