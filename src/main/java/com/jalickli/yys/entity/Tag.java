package com.jalickli.yys.entity;

import lombok.Data;

import java.util.List;

@Data
public class Tag {

    private Long id;  //id
    private String name;  //标签名字

    private List<Blog> blogList;

}
