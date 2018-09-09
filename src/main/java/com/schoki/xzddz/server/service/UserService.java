package com.schoki.xzddz.server.service;

import com.schoki.xzddz.server.model.po.User;

import java.util.List;

/**
 * @author : jzb219@gmail.com
 * @description : UserService
 * @date : 2018/9/9
 */
public interface UserService {
    /**
     * 查询所有用户信息
     *
     * @return list user
     */
    List<User> selectAll();

    /**
     * 添加User
     *
     * @param user user
     * @return result
     */
    int addUser(User user);
}
