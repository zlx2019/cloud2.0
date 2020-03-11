package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JavaBean硬编码 实现路由
 */
@Configuration
public class GatewayConfig {

    /**
     * 实现访问 localhsot:9527/guonei 转发到 http://news.baidu.com/guonei
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        //https://cn.bing.com/
        routes.route("path_route_bing",r->r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }

}
