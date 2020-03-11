package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 加上@RefreshScope注解后,如果修改了远程仓库的配置文件,对本服务发送post请求,即可刷新配置
 *  POST: http://localhost:3355/actuator/refresh
 */
@RestController
@Slf4j
@RefreshScope
public class ConfigClientController {

    @Value("${version}")
    private String version;

    @GetMapping("/version")
    public String version() {
        return version;
    }

}
