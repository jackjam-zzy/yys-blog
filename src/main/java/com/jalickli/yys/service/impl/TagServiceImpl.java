package com.jalickli.yys.service.impl;

import com.jalickli.yys.entity.Tag;
import com.jalickli.yys.mapper.TagMapper;
import com.jalickli.yys.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {


    @Autowired
    TagMapper tagMapper;

    @Override
    public List<Tag> getTagByBlogId(Long id) {
        return tagMapper.getTagByBlogId(id);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagMapper.getTagById(id);
    }

    @Override
    public void saveTag(Tag tag) {
        tagMapper.saveTag(tag);
    }

    @Override
    public List<Tag> listTag() {
        return tagMapper.listTag();
    }

    @Override
    public void updateTag(Tag tag, Long id) {
        tagMapper.updateTag(tag,id);
    }




    @Override
    public List<Tag> listTagByName(String name) {
        List<Tag> list = new ArrayList<>();
        if(name != null ){
            String[] idarray = name.split(",");
            for(int i=0;i<idarray.length;i++){
                list.add(tagMapper.getTagByName(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteTag(id);
    }

    @Override
    public List<Tag> getTagInfo() {
        return tagMapper.getTagInfo();
    }
}
