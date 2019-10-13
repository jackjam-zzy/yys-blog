package com.jalickli.yys.entity;

import lombok.Data;

import java.util.List;

@Data
public class Type {

    private Long id;  //id
    private String name;  //分类名字

    private List<Blog> blogList;

}
