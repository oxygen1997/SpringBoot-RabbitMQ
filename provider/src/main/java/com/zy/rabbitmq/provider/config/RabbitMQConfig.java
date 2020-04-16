package com.zy.rabbitmq.provider.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring容器初始化时会初始化带有Configuration注解的类中的@Bean
 */
@Configuration
public class RabbitMQConfig {

    /*
        直连交换机，精确匹配队列
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    /*
        与直连交换机绑定的队列
     */
    @Bean
    public Queue queue1(){
        return new Queue("q3",true);
    }

    /*
        将队列和交换机建立关系，q1队列和directExchange绑定路由键k1，形参q1要和声明队列的方法名一样，Spring自动注入功能
     */
    @Bean
    public Binding bingDirectExchange(Queue queue1,DirectExchange directExchange){
        return BindingBuilder.bind(queue1).to(directExchange).with("k3");
    }

}
