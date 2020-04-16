package com.zy.rabbitmq.provider.send;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
public class SenderByDirectExchange implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private String sendStatus;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String send(String msg){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("发送的消息为"+msg);
        this.rabbitTemplate.convertAndSend("directExchange","q2",msg,correlationData);
        return sendStatus;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("消息id:" + correlationData.getId());
        if (ack) {
            System.out.println("消息发送确认成功");
            this.sendStatus="success.  msgDataId ："+correlationData.toString();
        } else {
            System.out.println("消息发送确认失败:" + cause);
            this.sendStatus="fail. cause : "+cause;
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        try {
            System.out.println("return--message:" + new String(message.getBody(), "UTF-8") + ",replyCode:" + replyCode
                    + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
        } catch (UnsupportedEncodingException e) {
        }
    }


}
