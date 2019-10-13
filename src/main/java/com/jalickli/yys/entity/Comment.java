package com.jalickli.yys.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Comment {
    private Long id;  //id
    private String nickname;  //昵称
    private String email;  //邮箱
    private String content;  //文章内容
    private String avatar;  //头像
    private Date createTime;  //创建时间

    private Blog blog;
    private List<Comment> replyComment;
    private Comment parentComment;
}
