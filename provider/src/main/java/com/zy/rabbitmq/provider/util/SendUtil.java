package com.zy.rabbitmq.provider.util;

import com.zy.rabbitmq.provider.config.RabbitMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SendUtil {


    public void send(RabbitTemplate rabbitTemplate,String exchangeType,String routingKey){

        String createTime = RabbitMessage.getNowTime();
        String messageData = "This message send by [ "+ exchangeType +" ] , RoutingKey [ "+routingKey+" ] send time ：｛ "+createTime+" ｝";

        Map<Object, Object> map = new ConcurrentHashMap<>();
        map.put("messageId",RabbitMessage.getMsgId());
        map.put("messageData",messageData);
        map.put("createTime",createTime);

        rabbitTemplate.convertAndSend(exchangeType,routingKey ,map);

    }
}
