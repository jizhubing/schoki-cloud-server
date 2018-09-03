package com.schoki.xzddz.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaServer
@RestController //@RestController注解相当于 @Controller和ResponseBody
public class SchokiCloudServerApplication {

    /**
     * spring boot目录结构
     * - src
     *     -main
     *         -java
     *             -package
     *                 #主函数，启动类，运行它如果运行了 Tomcat、Jetty、Undertow 等容器
     *                 -SpringbootApplication
     *         -resouces
     *             #存放静态资源 js/css/images 等
     *             - statics
     *             #存放 html 模板文件
     *             - templates
     *             #主要的配置文件，SpringBoot启动时候会自动加载application.yml/application.properties
     *             - application.yml
     *     #测试文件存放目录
     *     -test
     *  # pom.xml 文件是Maven构建的基础，里面包含了我们所依赖JAR和Plugin的信息
     * - pom
     * @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(SchokiCloudServerApplication.class, args);
    }

    @GetMapping("/demo1")
    public String demo1(){
        return "Hello schoki";
    }

    /*@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext cxt){
        return args -> {
            System.out.println("来看看Springboot 默认为我们提供的Bean:");
            String[] beanName = cxt.getBeanDefinitionNames();
            Arrays.sort(beanName);
            Arrays.stream(beanName).forEach(System.out::println);
        };
    }*/



}
