package com.schoki.xzddz.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author : jzb219@gmail.com
 * @description : 配置文件
 * @date : 2018/9/2 11:17
 */
@Component
@ConfigurationProperties(prefix = "my2")
@PropertySource("classpath:application-dev.properties")
@Getter
@Setter
public class MyProperties2 {

    private int age;
    private String name;
    private String email;


    @Override
    public String toString() {
        return "Myproperties2{age=" + age + ",name=" + name + ",email=" + email + "}";
    }
}
