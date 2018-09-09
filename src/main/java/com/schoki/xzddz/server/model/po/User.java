package com.schoki.xzddz.server.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author : jzb219@gmail.com
 * @description : User实体
 * @date : 2018/9/5 21:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
@ApiModel("用户实体bean")
public class User implements Serializable {

    private static final long serialVersionUID = 3597684623259562731L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
