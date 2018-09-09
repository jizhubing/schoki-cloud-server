package com.schoki.xzddz.server.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : jzb219@gmail.com
 * @description : rabbit config
 * @date : 2018/9/9 20:42
 */
@Configuration
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
}
