package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * bus使用: 修改github上的配置文件后,向本服务(配置中心) 发送POST 请求 实时刷新配置
 *  curl -X POST "http://localhost:3344/actuator/bus-refresh"  -->广播所有服务刷新
 *  curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355" -->定点通知单个服务 服务名:端口号
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class,args);
    }
}
