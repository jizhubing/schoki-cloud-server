package com.schoki.xzddz.server.service.impl;

import com.schoki.xzddz.server.dao.UserMapper;
import com.schoki.xzddz.server.model.po.User;
import com.schoki.xzddz.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @author : jzb219@gmail.com
 * @description : UserServiceImpl
 * @date : 2018/9/9 14:45
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Resource(name = "redisTemplateForUser")
    private ValueOperations<String, User> userValueOperations;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public int addUser(User user) {
        //1.添加到数据库中
        int result = userMapper.insert(user);
        if (result == 1) {
            //2.add to redis
            userValueOperations.set("schoki_user", user);
        }
        return result;
    }
}
