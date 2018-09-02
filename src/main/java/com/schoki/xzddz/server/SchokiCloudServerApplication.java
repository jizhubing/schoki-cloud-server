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

    public static void main(String[] args) {
        SpringApplication.run(SchokiCloudServerApplication.class, args);
    }

    @GetMapping("/demo1")
    public String demo1(){
        return "Hello schoki";
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext cxt){
        return args -> {
            System.out.println("来看看Springboot 默认为我们提供的Bean:");
            String[] beanName = cxt.getBeanDefinitionNames();
            Arrays.sort(beanName);
            Arrays.stream(beanName).forEach(System.out::println);
        };
    }



}
