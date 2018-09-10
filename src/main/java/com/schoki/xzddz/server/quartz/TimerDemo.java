package com.schoki.xzddz.server.quartz;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author : jzb219@gmail.com
 * @description : TimerDemo
 * @date : 2018/9/10 9:44
 */
@Slf4j
public class TimerDemo {

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                log.info("执行定时任务，执行时间为：[{}]",LocalDate.now());
            }
        };
        //只要其中有一个因任务报错没有捕获抛出的异常，
        // 其它任务便会自动终止运行，使用 ScheduledExecutorService 则可以规避这个问题
        Timer timer = new Timer();
        timer.schedule(timerTask,5000,3000);
    }
}
