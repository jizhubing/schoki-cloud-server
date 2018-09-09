package com.schoki.xzddz.server.controller;

import com.schoki.xzddz.server.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : jzb219@gmail.com
 * @description : User控制器
 * @date : 2018/9/5 21:27
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<User> getUsers() {
        //查询所有用户
        String sql = "select * from t_user";
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(User.class));
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        String sql = "select * from t_user where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }


    @DeleteMapping("/{id}")
    private int delUser(@PathVariable Long id) {
        String sql = "delete from t_user where id- ?";
        return jdbcTemplate.update(sql, id);
    }

    @PostMapping
    public int addUser(@RequestBody User user) {
        String sql = "insert into t_user(username,password) values(?,?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    }

    @PutMapping("/{id}")
    public int editUser(@PathVariable Long id, @RequestBody User user) {
        String sql = "update t_user set username=? , password=? where id=?";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), id);
    }
}
