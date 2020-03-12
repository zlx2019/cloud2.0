package com.atguigu.springcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import com.atguigu.springcloud.service.MessageProvider;

import java.util.UUID;

@EnableBinding(Source.class)//定义消息的推送管道(生产者)
public class MessageProviderImpl implements MessageProvider {

    @Autowired
    private MessageChannel output;//消息推送信道

    @Override
    public String send() {
        String uuid = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(uuid).build());
        System.out.println("-------------provider发送流水号:" + uuid);
        return null;
    }
}
