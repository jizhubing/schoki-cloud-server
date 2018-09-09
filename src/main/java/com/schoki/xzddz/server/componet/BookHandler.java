package com.schoki.xzddz.server.componet;

import com.rabbitmq.client.Channel;
import com.schoki.xzddz.server.config.RabbitConfig;
import com.schoki.xzddz.server.model.po.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @author : jzb219@gmail.com
 * @description : 消费队列 TODO ，需要调整后期
 * @date : 2018/9/9 21:06
 */
@Component
@Slf4j
public class BookHandler {

    @RabbitListener(queues = {RabbitConfig.DEFAULT_BOOK_QUEUE})
    public void listenerAutoAck(Book book, Message message, Channel channel) {
        // 如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            log.info("listenerAutoAck 监听的消息：[{}]", book.toString());
            channel.basicAck(deliveryTag, false);

        } catch (IOException e) {
            try {
                channel.basicRecover();
            } catch (IOException e1) {
                e.printStackTrace();
            }
        }
    }

    @RabbitListener(queues = {RabbitConfig.MANUAL_BOOK_QUEUE})
    public void listenerManualAck(Book book, Message message, Channel channel) {
        log.info("ListenerManual 监听的消息：[{}]",book.toString());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RabbitListener(queues = {RabbitConfig.REGISTER_QUEUE_NAME})
    public void listenerDelayQueue(Book book , Message message,Channel channel) {
        log.info("listenerDelayQueue监听消息-消费时间-[{}],[{}]",LocalDate.now(),book.toString());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
