package com.schoki.xzddz.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Async
@EnableScheduling
@SpringBootApplication
//@EnableEurekaClient
@RestController //@RestController注解相当于 @Controller和ResponseBody
@EnableSwagger2
//@EnableAdminServer
public class SchokiCloudServerApplication {

    /**
     * spring boot目录结构
     * - src
     * -main
     * -java
     * -package
     * #主函数， 启动类，运行它如果运行了 Tomcat、Jetty、Undertow 等容器
     * -SpringbootApplication
     * -resouces
     * #存放静态资源 js/css/images 等
     * - statics
     * #存放 html 模板文件
     * - templates
     * #主要的配置文件，SpringBoot启动时候会自动加载application.yml/application.properties
     * - application.yml
     * #测试文件存放目录
     * -test
     * # pom.xml 文件是Maven构建的基础，里面包含了我们所依赖JAR和Plugin的信息
     * - pom
     *
     * @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(SchokiCloudServerApplication.class, args);
    }

    @GetMapping("/demo1")
    public String demo1() {
        return "Hello schoki";
    }

    /**
     * 很关键：默认情况下 TaskScheduler 的 poolSize = 1
     *
     * @return 线程池
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }


   /* @Profile("dev")
    @Configuration
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable();
        }
    }

    @Profile("prod")
    @Configuration
    @Data
    public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
        private final String adminContextPath;

        public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
            this.adminContextPath = adminServerProperties.getContextPath();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
            successHandler.setTargetUrlParameter("redirectTo");
            http.authorizeRequests()
                    .antMatchers(adminContextPath + "/assets/**").permitAll()
                    .antMatchers(adminContextPath + "/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                    .logout().logoutUrl(adminContextPath + "/logout").and()
                    .httpBasic().and()
                    .csrf().disable();
        }


    }*/

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
