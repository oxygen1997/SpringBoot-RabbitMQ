package com.zy.rabbitmq.provider.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.UUID;

public class RabbitMessage {

    public static String SUCCESS_TIPS = "send success by ";
    public static String FAIL_TIPS = "send failed by ";

    //exchange name
    public static String DIRECT_EXCHANGE="DirectExchange";
    public static String TOPIC_EXCHANGE="TopicExchange";
    public static String FANOUT_EXCHANGE="FanoutExchange";

    //queue name
    public static String QUEUE_DIRECT="q1.direct";
    public static String QUEUE_TOPIC1="q2.topic";
    public static String QUEUE_TOPIC2="q3.topic";
    public static String QUEUE_FANOUT1="q4.fanout";
    public static String QUEUE_FANOUT2="q5.fanout";


    //routing key
    public static String ROUTINGKEY_DIRECT="k1.direct";
    public static String ROUTINGKEY_TOPIC1="#.k2.*";
    public static String ROUTINGKEY_TOPIC2="*.k2.#";


    //get now  method
    public static String getNowTime(){
        return  LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    //set msg id method
    public static String getMsgId(){
        return String.valueOf(UUID.randomUUID());
    }


}
