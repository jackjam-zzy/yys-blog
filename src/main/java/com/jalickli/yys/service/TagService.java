package com.jalickli.yys.service;


import com.jalickli.yys.entity.Tag;

import java.util.List;

public interface TagService {
    Tag getTagById(Long id);
    void saveTag(Tag tag);
    List<Tag> listTag();
    List<Tag> listTagByName(String name);
    void updateTag(Tag tag,Long id);
    void deleteTag(Long id);
    List<Tag> getTagByBlogId(Long id);
    List<Tag> getTagInfo();
}
