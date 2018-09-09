package com.schoki.xzddz.server.dao;

import com.schoki.xzddz.server.model.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : jzb219@gmail.com
 * @description : UserMapper
 * @date : 2018/9/9
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名称查询
     *
     * @param username :username
     * @return list
     */
    @Select("SELECT * FROM t_user WHERE username=#{username}")
    List<User> getByUsername(@Param("username") String username);


    /**
     * 保存用户信息
     * @param user : user
     * @return int
     */
    int insert(User user);
}
