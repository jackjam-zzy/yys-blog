package com.jalickli.yys.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jalickli.yys.entity.Blog;
import com.jalickli.yys.entity.Tag;
import com.jalickli.yys.entity.User;
import com.jalickli.yys.service.BlogService;
import com.jalickli.yys.service.TagService;
import com.jalickli.yys.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(Model model,
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

        model.addAttribute("types",typeService.listType());

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        Page<Blog> page = PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Blog> blogList = blogService.listBlogAll();
            PageInfo<Blog> pageInfo = new PageInfo<>(blogList,pageSize);
            model.addAttribute("pageInfo",pageInfo);
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "admin/blogs";
    }

    @GetMapping("/blogs/publish")
    public String publish(Model model){

        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());

        return "admin/publish";
    }

    @PostMapping("/blogs")
    public String publishPost(HttpServletRequest request,
                              Blog blog,
                              HttpSession session,
                              RedirectAttributes attributes){

        blog.setUser((User)session.getAttribute("user"));
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        blog.setTagList(tagService.listTagByName(request.getParameter("tag")));
        Blog b;
        if (blog.getId() == null) {
            b =  blogService.saveBlog(blog);
        } else {
            blogService.updateBlog(blog,blog.getId());
            b = blog;
        }

        if (b == null ) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }

        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/edit/{id}")
    public String editBlog(@PathVariable Long id,
                           Model model){

        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog",blog);
        List<Tag> list = blog.getTagList();
        String str = "";
        if(list != null){
            for(int i=0;i<list.size();i++){
                if(i==0){
                    str = str + list.get(i).getName();
                }else {
                    str = str + "," + list.get(i).getName();
                }
            }
        }
        model.addAttribute("tagString",str);

        return "/admin/edit";
    }

    @GetMapping("/blogs/delete/{id}")
    public String deleteBlog(@PathVariable Long id,
                             RedirectAttributes attributes){
        blogService.deleteBlog(id);
        if(id != null){
            attributes.addFlashAttribute("message","删除成功");
        }
        return "redirect:/admin/blogs";
    }

}
