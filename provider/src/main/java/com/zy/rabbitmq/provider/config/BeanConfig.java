package com.zy.rabbitmq.provider.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring容器初始化时会初始化带有Configuration注解的类中的@Bean属性
 */
@Configuration
public class BeanConfig {

    /*
        =============直连交换机，精确匹配队列======================
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(RabbitMessage.DIRECT_EXCHANGE);
    }

    /*
        与直连交换机绑定的队列
     */
    @Bean
    public Queue queue1(){
        return new Queue(RabbitMessage.QUEUE_DIRECT,true);
    }

    /*
        将队列和交换机建立关系，q1队列和directExchange绑定路由键k1，形参q1要和声明队列的方法名一样，Spring自动注入功能
     */
    @Bean
    public Binding bingDirectExchange(Queue queue1,DirectExchange directExchange){
        return BindingBuilder.bind(queue1).to(directExchange).with(RabbitMessage.ROUTINGKEY_DIRECT);
    }


    /*
       =============通配符交换机，模糊匹配队列======================
    */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(RabbitMessage.TOPIC_EXCHANGE);
    }

    /*
        与通配符交换机绑定的队列
     */
    @Bean
    public Queue queue2(){
        return new Queue(RabbitMessage.QUEUE_TOPIC1,true);
    }

    /*
        与通配符交换机绑定的队列
     */
    @Bean
    public Queue queue3(){
        return new Queue(RabbitMessage.QUEUE_TOPIC2,true);
    }

    /*
        将队列和交换机建立关系，q2队列和topicExchange绑定路由键k2，形参q2要和声明队列的方法名一样，Spring自动注入功能
     */
    @Bean
    public Binding bingTopicExchange1(Queue queue2,TopicExchange topicExchange){
        return BindingBuilder.bind(queue2).to(topicExchange).with(RabbitMessage.ROUTINGKEY_TOPIC1);
    }
    /*
        将队列和交换机建立关系，q2队列和topicExchange绑定路由键k2，形参q2要和声明队列的方法名一样，Spring自动注入功能
     */
    @Bean
    public Binding bingTopicExchange2(Queue queue3,TopicExchange topicExchange){
        return BindingBuilder.bind(queue3).to(topicExchange).with(RabbitMessage.ROUTINGKEY_TOPIC2);
    }



    /*
       =============广播交换机======================
    */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(RabbitMessage.FANOUT_EXCHANGE);
    }

    /*
        与广播交换机绑定的队列,不需要匹配路由键
     */
    @Bean
    public Queue queue4(){
        return new Queue(RabbitMessage.QUEUE_FANOUT1,true);
    }

    /*
        与广播交换机绑定的队列
     */
    @Bean
    public Queue queue5(){
        return new Queue(RabbitMessage.QUEUE_FANOUT2,true);
    }

    /*
        将队列和交换机建立关系，q3队列和fanoutExchange绑定，形参q3要和声明队列的方法名一样，Spring自动注入功能
     */
    @Bean
    public Binding bingFanoutExchange1(Queue queue4,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue4).to(fanoutExchange);
    }

    /*
      将队列和交换机建立关系，q4队列和fanoutExchange绑定，形参q3要和声明队列的方法名一样，Spring自动注入功能
   */
    @Bean
    public Binding bingFanoutExchange2(Queue queue5,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue5).to(fanoutExchange);
    }

}
