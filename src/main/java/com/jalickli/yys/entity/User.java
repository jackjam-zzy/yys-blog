package com.jalickli.yys.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;  //用户权限级别
    private Date createTime;
    private Date modifyTime;

    private List<Blog> blogList;

}
