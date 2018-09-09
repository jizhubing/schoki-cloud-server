package com.schoki.xzddz.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoki.xzddz.server.model.po.User;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author : jzb219@gmail.com
 * @description : RedisCacheAutoConfiguration
 * @date : 2018/9/9 15:29
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisCacheAutoConfiguration {

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }


    /**
     * 默认情况下的模板只支持RedisTemplate<String,String>，也就是只支持字符串，不太友好，所以需要自定义模板
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String,Serializable> redisTemplate(LettuceConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * RedisTemplate<String, User> template = new RedisTemplate<>();
     * 		template.setConnectionFactory(redisConnectionFactory);
     * 		template.setEnableTransactionSupport(true);
     *
     * 		RedisSerializer<String> stringRedisSerializer = stringRedisSerializer();
     * 		Jackson2JsonRedisSerializer<User> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(User.class);
     * 		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
     *
     * 		template.setKeySerializer(stringRedisSerializer);
     * 		template.setValueSerializer(jackson2JsonRedisSerializer);
     * 		template.afterPropertiesSet();
     * 		return template;
     * @param redisConnectionFactory
     * @return
     */
    @Bean("redisTemplateForUser")
    public RedisTemplate<String,User> userRedisTemplate(LettuceConnectionFactory redisConnectionFactory, ObjectMapper objectMapper){
        RedisTemplate<String,User> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setEnableTransactionSupport(true);

        RedisSerializer<String> stringRedisSerializer = stringRedisSerializer();
        Jackson2JsonRedisSerializer<User> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(User.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

}
