package com.schoki.xzddz.server.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : jzb219@gmail.com
 * @description : 作者VO
 * @date : 2018/9/3 22:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    private Integer age;

    private String email;

    private String name;

}
