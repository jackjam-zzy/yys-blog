package com.jalickli.yys.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jalickli.yys.entity.Blog;
import com.jalickli.yys.entity.Tag;
import com.jalickli.yys.entity.Type;
import com.jalickli.yys.service.BlogService;
import com.jalickli.yys.service.TagService;
import com.jalickli.yys.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                        @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){
        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 5;    //设置默认每页显示的数据数
        }



        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        Page<Blog> page = PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Blog> blogList = blogService.listBlogWherePublish(true);
            PageInfo<Blog> pageInfo = new PageInfo<>(blogList,pageSize);
            model.addAttribute("pageInfo",pageInfo);
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        Page<Type> typePage = PageHelper.startPage(1, 8);
        try {
            List<Type> typeList = typeService.listType();
            PageInfo<Type> pageInfotype = new PageInfo<>(typeList,pageSize);
            model.addAttribute("pageInfoType",pageInfotype);
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        Page<Tag> tagPage = PageHelper.startPage(1, 8);
        try {
            List<Tag> tagList = tagService.getTagInfo();
            PageInfo<Tag> tagInfo = new PageInfo<>(tagList,pageSize);
            model.addAttribute("tagInfo",tagInfo);
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }


        Page<Blog> titlePage = PageHelper.startPage(1, 8);
        try {
            List<Blog> stringList = blogService.findTitleByRecommend();
            PageInfo<Blog> pageInfoTitle = new PageInfo<>(stringList,pageSize);
            model.addAttribute("pageInfoTitle",pageInfoTitle);
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }


        return "index";
    }


    @GetMapping("/tag")
    public String toTag(){
        return "tag";
    }


    @GetMapping("/type")
    public String toType(){
        return "type";
    }

    @GetMapping("/archive")
    public String toArchive(){
        return "archive";
    }

    @GetMapping("/blog/{id}")
    public String toBlog(@PathVariable("id") Long id,
                         Model model){
        model.addAttribute("blog",blogService.getBlogById(id));
        return "blog";
    }

}
