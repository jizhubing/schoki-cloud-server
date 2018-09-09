package com.schoki.xzddz.server.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.schoki.xzddz.server.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author : jzb219@gmail.com
 * @description : UserMapperTest
 * @date : 2018/9/9 13:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        final int row1 = userMapper.insert(User.builder().username("u1").password("p1").build());
        log.info("[添加结果] - [{}]", row1);
        final int row2 = userMapper.insert(User.builder().username("u2").password("p2").build());
        log.info("[添加结果] - [{}]", row2);
        final int row3 = userMapper.insert(User.builder().username("u1").password("p3").build());
        log.info("[添加结果] - [{}]", row3);
        final List<User> u1 = userMapper.getByUsername("u1");
        log.info("[根据用户名查询] - [{}]", u1);

        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());
        userMapper.insert(User.builder().username("u1").password("p1").build());

        //分页排序
        PageInfo<Object> pageInfo = PageHelper.startPage(1, 30).setOrderBy("id desc").doSelectPageInfo(() -> this.userMapper.selectAll());
        log.info("lambda 写法：-> 分页信息：[{}]",pageInfo.toString());

        PageHelper.startPage(1,10).setOrderBy("id desc");
        PageInfo<User> userPageInfo = new PageInfo<>(userMapper.selectAll());
        log.info("普通写法:[{}]",userPageInfo);
    }

}
