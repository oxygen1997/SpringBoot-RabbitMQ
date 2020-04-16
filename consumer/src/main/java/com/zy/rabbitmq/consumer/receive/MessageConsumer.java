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
    @RabbitListener(queues = "q1.direct")
    public void receiveDirect(Map map, Channel channel) throws IOException {
        try {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("接受到q1.direct的消息为："+map.get("messageData").toString());
        } catch (Exception e) {
            e.printStackTrace();
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }
    }

    @RabbitHandler
    @RabbitListener(queues = {"q2.topic"})
    public void receiveTopic1(Map map, Channel channel) throws IOException {
        try {
            System.out.println("接受到q2.topic的消息为："+map.get("messageData").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = {"q3.topic"})
    public void receiveTopic2(Map map, Channel channel) throws IOException {
        try {
            System.out.println("接受到q3.topic的消息为："+map.get("messageData").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = {"q4.fanout"})
    public void receiveFanout1(Map map, Channel channel) throws IOException {
        try {
            System.out.println("接受到q4.fanout的消息为："+map.get("messageData").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = {"q5.fanout"})
    public void receiveFanout2(Map map, Channel channel) throws IOException {
        try {
            System.out.println("接受到q5.fanout的消息为："+map.get("messageData").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
