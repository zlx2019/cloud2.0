package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 网关过滤,日志记录
 */
@Component
@Slf4j
public class LogGatewayFilter implements GlobalFilter, Ordered {

    /**
     * 自定义过滤
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("------------common LogFilter :  "+new Date() +"------------------");
        ServerHttpRequest request = exchange.getRequest();
        String uname = request.getQueryParams().getFirst("uname");
        if (uname == null){
            log.warn("uname为null,非法登陆!");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        log.info(uname + "登陆!");
        return chain.filter(exchange);
    }

    /**
     * 优先级 越小越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
