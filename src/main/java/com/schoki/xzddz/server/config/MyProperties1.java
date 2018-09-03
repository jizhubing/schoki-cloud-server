package com.schoki.xzddz.server.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : jzb219@gmail.com
 * @description : 映射properties配置文件
 * @date : 2018/9/2 10:58
 */
@Component
@ConfigurationProperties(prefix = "my1")
@Getter
@Setter
public class MyProperties1 {

    private Integer age;
    private String name;

    @Override
    public String toString() {
        return "MyProperties{age=" + age + ",name=" + name+"}";
    }
}
