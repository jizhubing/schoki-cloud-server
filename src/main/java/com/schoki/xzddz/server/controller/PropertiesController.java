package com.schoki.xzddz.server.controller;

import com.schoki.xzddz.server.config.MyProperties1;
import com.schoki.xzddz.server.config.MyProperties2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : jzb219@gmail.com
 * @description : 配置文件控制器
 * @date : 2018/9/2 11:10
 */
@RequestMapping("/properties")
@RestController
@Slf4j
public class PropertiesController {


    private final MyProperties1 myProperties1;

    private final MyProperties2 myProperties2;

    @Autowired
    public PropertiesController(MyProperties1 myProperties1, MyProperties2 myProperties2) {
        this.myProperties1 = myProperties1;
        this.myProperties2 = myProperties2;
    }

    @GetMapping("/1")
    public MyProperties1 myProperties1(){
        log.info("**********************************************************");
        log.info(myProperties1.toString());
        log.info("**********************************************************");
        return myProperties1;
    }

    @GetMapping("/2")
    public MyProperties2 myProperties2() {
        log.info("**********************************************************");
        log.info(myProperties2.toString());
        log.info("**********************************************************");
        return myProperties2;
    }
}
