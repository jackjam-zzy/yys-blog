package com.jalickli.yys.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Blog {

    private Long id;   //id
    private String title;   //标题
    private String description;  //博客描述
    private String content;  //内容
    private String picture;  //图片
    private String flag;    //标记
    private Integer view;  //浏览次数
    private boolean appreciate;  //赞赏开启
    private boolean copyrightOpening;  //版权开启
    private boolean comment;  //评论开启
    private boolean published;  //发布
    private boolean recommend;  //推荐
    private Date createTime;  //创建时间
    private Date modifyTime;  //修改时间

    private Type type;
    private List<Tag> tagList;
    private User user;
    private List<Comment> commentList;

}
