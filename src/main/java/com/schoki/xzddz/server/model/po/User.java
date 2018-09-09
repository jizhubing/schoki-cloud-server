package com.schoki.xzddz.server.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class User implements Serializable {

    private static final long serialVersionUID = 3597684623259562731L;

    private Long id;
    private String username;
    private String password;
}
