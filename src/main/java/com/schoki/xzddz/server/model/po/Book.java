package com.schoki.xzddz.server.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : jzb219@gmail.com
 * @description : Book实体
 * @date : 2018/9/9 20:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    private static final long serialVersionUID = 8874050306850680813L;

    private String id;

    private String name;
}
