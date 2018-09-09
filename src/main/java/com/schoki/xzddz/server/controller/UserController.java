package com.schoki.xzddz.server.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.schoki.xzddz.server.model.po.User;
import com.schoki.xzddz.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "用户相关接口" , description = "用户相关接口")
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    private final UserService userService;

    @Autowired
    public UserController(JdbcTemplate jdbcTemplate, UserService userService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
    }

    @GetMapping("/getUserByPage")
    @ApiOperation(value = "分页用户信息", response = PageInfo.class, notes = "分页用户信息", httpMethod = "GET")
    public PageInfo<User> getUserByPage() {
        PageInfo<User> userPageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> userService.selectAll());
        return userPageInfo;
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
    @ApiOperation(value = "添加用户接口" , notes = "添加员工接口")
    public int addUser(@RequestBody User user) {
       /* String sql = "insert into t_user(username,password) values(?,?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword());*/
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public int editUser(@PathVariable Long id, @RequestBody User user) {
        String sql = "update t_user set username=? , password=? where id=?";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), id);
    }
}
