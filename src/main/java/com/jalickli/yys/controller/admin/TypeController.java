package com.jalickli.yys.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jalickli.yys.entity.Type;
import com.jalickli.yys.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(Model model,
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
        Page<Type> page = PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Type> userList = typeService.listType();
//            System.out.println("分页数据："+userList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Type> pageInfo = new PageInfo<>(userList,pageSize);
//            System.out.println("分页数据："+pageInfo);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
            }
        return "admin/types";
        }

    @GetMapping("/types/add")
    public String typeAdd(){
        return "admin/typeadd";
    }

    @PostMapping("/types")
    public String addPost(Type type,
                          RedirectAttributes attributes){

        if(type != null){
            attributes.addFlashAttribute("message","添加成功");
        }
        typeService.saveType(type);

        //重定向，再次获取刷新
        return "redirect:/admin/types";
    }

    @GetMapping("/types/edit/{id}")
    public String edit(@PathVariable Long id,
                       Model model){
        Type type = typeService.getTypeById(id);
        model.addAttribute("type",type);
        return "/admin/typeedit";
    }

    @PostMapping("/types/{id}")
    public String editPost(Type type,
                           @PathVariable Long id,
                           RedirectAttributes attributes){
//        System.out.println("type："+type);
        if(type != null){
            attributes.addFlashAttribute("message","修改成功");
        }
        typeService.updateType(type,id);
        return "redirect:/admin/types";
    }

    @GetMapping("/types/delete/{id}")
    public String deleteType(@PathVariable Long id,
                             RedirectAttributes attributes){
        typeService.deleteType(id);
        if(id != null){
            attributes.addFlashAttribute("message","删除成功");
        }
        return "redirect:/admin/types";
    }

}
