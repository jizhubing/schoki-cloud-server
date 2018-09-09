package com.schoki.xzddz.server.controller;

import com.schoki.xzddz.server.config.RabbitConfig;
import com.schoki.xzddz.server.model.po.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : jzb219@gmail.com
 * @description : Book控制器
 * @date : 2018/9/9 20:57
 */
@RestController
@RequestMapping(value = "/book")
@Slf4j
public class BookController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public BookController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @GetMapping
    public void defaultMessage() {
        Book book = Book.builder().id("1").name("我是陈冠希").build();
        this.rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_BOOK_QUEUE, book);
        this.rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_BOOK_QUEUE, book);

    }


}
