package com.goldcard.spring_boot_redis_demo.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        // 消息体
        String body = new String(message.getBody());
        // 渠道名称
        String topic = new String(bytes);
        System.out.println(body);
        System.out.println(topic);
    }
}
