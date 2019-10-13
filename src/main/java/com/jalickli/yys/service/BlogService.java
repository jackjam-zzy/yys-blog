package com.jalickli.yys.service;

import com.jalickli.yys.entity.Blog;

import java.util.List;

public interface BlogService {

    /*
    * 查
    * */
    Blog getBlogById(Long id);

    List<Blog> listBlogAll();

    List<Blog> listBlogWherePublish(Boolean published);

    List<Blog> listBlogByForm(Blog blog);

    /*
    *改
    * */
    void updateBlog(Blog blog,Long BlogId);

    /*
    * 增
    * */
    Blog saveBlog(Blog blog);

    /*
    * 删
    * */
    void deleteBlog(Long id);

    /*
    * 根据最新推荐来查询
    * */
    List<Blog> findTitleByRecommend();




}
