package com.nacos.storage.controller;

import com.nacos.storage.mq.TopicSender;
import com.nacos.storage.param.MqParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/mq")
public class MqController {

    @Resource
    TopicSender topicSender;

    @PostMapping("/send")
    public String sendMessage(@RequestBody MqParam param){
        if(param.isOne()){
            topicSender.send_one();
        }else{
            topicSender.send_two();
        }
        return "send success";
    }


}
