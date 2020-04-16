package com.zy.rabbitmq.provider.controller;

import com.zy.rabbitmq.provider.config.RabbitMessage;
import com.zy.rabbitmq.provider.util.SendUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MessageController {

  
    private String sendStatus;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/direct")
    public String sendDirectMessage() {

        try{
            SendUtil sendUtil = new SendUtil();
            sendUtil.send(rabbitTemplate,RabbitMessage.DIRECT_EXCHANGE,RabbitMessage.ROUTINGKEY_DIRECT);
            sendStatus=RabbitMessage.SUCCESS_TIPS+RabbitMessage.DIRECT_EXCHANGE;
            System.out.println(sendStatus);
        }catch (Exception e){
            e.printStackTrace();
            sendStatus=RabbitMessage.FAIL_TIPS+RabbitMessage.DIRECT_EXCHANGE;
            System.out.println(sendStatus);
        }
        return sendStatus;
    }

    @GetMapping("/topic")
    public String sendTopicMessage() {

        String routingKey1 = "one.k2.topic.a.b";
        String routingKey2 = "topic.c.d.k2.one";

        try{
            //key=one.k2.topic.a.b bind q3.topic--->*.k2.#    #匹配0个或多个，*匹配一个
            SendUtil sendUtil = new SendUtil();
            sendUtil.send(rabbitTemplate,RabbitMessage.TOPIC_EXCHANGE,routingKey1);
            System.out.println(RabbitMessage.SUCCESS_TIPS+RabbitMessage.TOPIC_EXCHANGE+" first msg...");

            System.out.println("... pause 1 s ......");
            Thread.sleep(1000);

            //key=c.d.e.k2.topic bind q2.topic--->#.k2.*   #匹配0个或多个，*匹配一个
            sendUtil.send(rabbitTemplate,RabbitMessage.TOPIC_EXCHANGE,routingKey2);

            sendStatus= RabbitMessage.SUCCESS_TIPS+RabbitMessage.TOPIC_EXCHANGE+" second msg...";
            System.out.println(sendStatus);

        }catch (Exception e){
            e.printStackTrace();
            sendStatus=RabbitMessage.FAIL_TIPS+RabbitMessage.TOPIC_EXCHANGE;
            System.out.println(sendStatus);
        }
        return sendStatus;
    }


    @GetMapping("/fanout")
    public String sendFanoutMessage() {

        try{
            SendUtil sendUtil = new SendUtil();
            sendUtil.send(rabbitTemplate,RabbitMessage.FANOUT_EXCHANGE,null);
            sendStatus= RabbitMessage.SUCCESS_TIPS+RabbitMessage.FANOUT_EXCHANGE;
            System.out.println(sendStatus);
        }catch (Exception e){
            e.printStackTrace();
            sendStatus=RabbitMessage.FAIL_TIPS+RabbitMessage.FANOUT_EXCHANGE;
            System.out.println(sendStatus);
        }
        return sendStatus;
    }


}
