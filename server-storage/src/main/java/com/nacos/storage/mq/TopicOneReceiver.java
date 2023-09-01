package com.nacos.storage.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.one")
public class TopicOneReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Topic One : " + hello);
    }

}
