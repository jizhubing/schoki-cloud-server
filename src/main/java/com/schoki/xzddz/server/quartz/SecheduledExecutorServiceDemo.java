package com.schoki.xzddz.server.quartz;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : jzb219@gmail.com
 * @description :SecheduledExecutorServiceDemo
 * @date : 2018/9/10 9:51
 */
@Slf4j
public class SecheduledExecutorServiceDemo {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        service.scheduleAtFixedRate(() -> log.info("开始执行任务：[{}]",LocalDate.now()),0 ,3, TimeUnit.SECONDS);
    }
}
