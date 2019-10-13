package com.jalickli.yys.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jalickli.yys.entity.Tag;
import com.jalickli.yys.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(Model model,
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
//        System.out.println("当前页是："+pageNum+"显示条数是："+pageSize);

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        Page<Tag> page = PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Tag> tagList = tagService.listTag();
//            System.out.println("分页数据："+userList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Tag> pageInfo = new PageInfo<>(tagList,pageSize);
//            System.out.println("分页数据："+pageInfo);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "admin/tags";
    }

    @GetMapping("/tags/add")
    public String tagAdd(){
        return "admin/tagadd";
    }

    @PostMapping("/tags")
    public String addPost(Tag tag,
                          RedirectAttributes attributes){

        if(tag != null){
            attributes.addFlashAttribute("message","添加成功");
        }
        tagService.saveTag(tag);

        //重定向，再次获取刷新
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/edit/{id}")
    public String edit(@PathVariable Long id,
                       Model model){
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag",tag);
        return "/admin/tagedit";
    }

    @PostMapping("/tags/{id}")
    public String editPost(Tag tag,
                           @PathVariable Long id,
                           RedirectAttributes attributes){
//        System.out.println("type："+type);
        if(tag != null){
            attributes.addFlashAttribute("message","修改成功");
        }
        tagService.updateTag(tag,id);
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/delete/{id}")
    public String deleteTag(@PathVariable Long id,
                             RedirectAttributes attributes){
        tagService.deleteTag(id);
        if(id != null){
            attributes.addFlashAttribute("message","删除成功");
        }
        return "redirect:/admin/tags";
    }



}
