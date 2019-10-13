package com.jalickli.yys.service.impl;

import com.jalickli.yys.entity.Blog;
import com.jalickli.yys.entity.Tag;
import com.jalickli.yys.mapper.BlogMapper;
import com.jalickli.yys.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<Blog> listBlogAll() {
        return blogMapper.listBlogAll();
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public List<Blog> listBlogByForm(Blog blog) {
        return blogMapper.listBlogByForm(blog);
    }

    @Override
    public void updateBlog(Blog blog,Long blogId) {
        blog.setModifyTime(new Date());
        //更新blog数据
        blogMapper.updateBlog(blog,blogId);
        //先删除
        blogMapper.deleteBlogTagRelationship(blogId);
        List<Tag> tagList = blog.getTagList();
        for(int i = 0;i<tagList.size();i++){
            blogMapper.insertBlogTagRelationship(blogId,tagList.get(i).getId());
        }

    }

    @Override
    public Blog saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setModifyTime(new Date());
        blog.setView(0);
        blogMapper.saveBlog(blog);
        Long id = blog.getId();
        List<Tag> list = blog.getTagList();
        for(int i=0;i<list.size();i++){
            blogMapper.saveBlogTag(id,list.get(i).getId());
        }
        return blog ;
    }

    public List<Blog> listBlogWherePublish(Boolean published) {
        return blogMapper.listBlogWherePublish(published);
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlogTagRelationship(id);
        blogMapper.deleteBlog(id);
    }

    @Override
    public List<Blog> findTitleByRecommend() {
        return blogMapper.findTitleByRecommend();
    }
}
