package com.zy.rabbitmq.consumer.receive;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class MessageConsumer {
    @RabbitHandler
    @RabbitListener(queues = "q3")
    public void receive(Map map, Channel channel) throws IOException {
        try {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("接受到的消息为："+map.get("messageData").toString());
        } catch (Exception e) {
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }
    }

}
