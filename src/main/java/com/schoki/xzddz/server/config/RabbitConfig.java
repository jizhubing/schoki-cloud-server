package com.schoki.xzddz.server.config;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author : jzb219@gmail.com
 * @description : rabbit config
 * @date : 2018/9/9 20:42
 */
@Configuration
@Slf4j
public class RabbitConfig {

    public static final String DEFAULT_BOOK_QUEUE = "dev.book.register.default.queue";
    public static final String MANUAL_BOOK_QUEUE = "dev.book.register.manual.queue";

    @Bean
    public Queue defaultBookQueue() {
        //第一个是QUEUE的名字，第二个是消息是否需要持久化配置
        return new Queue(DEFAULT_BOOK_QUEUE, true);
    }

    @Bean
    public Queue manualBookQueue() {
        //第一个是QUEUE的名字，第二个是消息是否需要持久化处理
        return new Queue(MANUAL_BOOK_QUEUE, true);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {
        cachingConnectionFactory.setPublisherConfirms(true);
        cachingConnectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
                log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

    /**
     * 延迟队列delay名称
     */
    private static final String REGISTER_DELAY_QUEUE = "dev.book.register.delay.queue";

    /**
     * DLX , dead letter发送到的exchange
     */
    public static final String REGISTER_DELAY_EXCHANGE = "dev.book/register.delay.exchange";

    public static final String DELAY_ROUTING_KEY = "";

    public static final String REGISTER_QUEUE_NAME = "dev.book.register.queue";
    public static final String REGISTER_EXCHANGE_NAME = "dev.book.register.exchange";
    public static final String ROUTING_KEY = "all";

    @Bean
    public Queue delayProcessQueue() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("x-dead-letter-exchange", REGISTER_EXCHANGE_NAME);
        params.put("x-dead-letter-routing-key", ROUTING_KEY);
        return new Queue(REGISTER_DELAY_QUEUE, true, false, false, params);
    }


    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(REGISTER_DELAY_EXCHANGE);
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(delayProcessQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY);
    }

    @Bean
    public Queue registerBookQueue() {
        return new Queue(REGISTER_QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange registerBookTopicExchange() {
        return new TopicExchange(REGISTER_EXCHANGE_NAME);
    }

    @Bean
    public Binding registerBookBinding() {
        //
        return BindingBuilder.bind(registerBookQueue()).to(registerBookTopicExchange()).with(ROUTING_KEY);
    }






}
