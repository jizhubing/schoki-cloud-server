package com.schoki.xzddz.server.service.impl;

import com.schoki.xzddz.server.dao.UserMapper;
import com.schoki.xzddz.server.model.po.User;
import com.schoki.xzddz.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : jzb219@gmail.com
 * @description : UserServiceImpl
 * @date : 2018/9/9 14:45
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
