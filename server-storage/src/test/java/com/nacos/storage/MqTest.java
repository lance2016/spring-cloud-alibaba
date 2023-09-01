package com.nacos.storage;

import com.nacos.storage.mq.HelloReceiver;
import com.nacos.storage.mq.HelloSender;
import com.nacos.storage.mq.TopicSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MqTest extends BaseTests {


    @Autowired
    HelloSender helloSender;

    @Autowired
    TopicSender topicSender;


    @Test
    public void testSend(){
        helloSender.send();
    }



    @Test
    public void testSendTopic() throws InterruptedException {

       while(true){
           topicSender.send_two();
           Thread.sleep(5000);
           topicSender.send_one();
           Thread.sleep(5000);
       }

    }
}
