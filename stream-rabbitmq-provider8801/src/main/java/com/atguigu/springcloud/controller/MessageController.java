package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageProvider messageProvider;

    @GetMapping("/send")
    public String send(){
        messageProvider.send();
        return "发送成功!";
    }

}
