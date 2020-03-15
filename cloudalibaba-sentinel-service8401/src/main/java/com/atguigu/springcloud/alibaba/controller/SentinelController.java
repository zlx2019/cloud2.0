package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SentinelController {

    @GetMapping("/testA")
    public String testA(){
        return "testA----";
    }


    @GetMapping("/testB")
    public String testB(){
        return "testB----";
    }


    @GetMapping("/testD")
    public String testD(){
        log.info("testD 测试RT");
        int i = 10/0;
        return "testD----";
    }


    /**
     * 热点限流
     * blockHandler,异常后的回调函数
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,@RequestParam(value = "p2",required = false)String p2){
        return "testHotKey----";
    }
    public String deal_testHotKey(String p1, String p2, BlockException e){
        return "deal_testHotKey";
    }
}
