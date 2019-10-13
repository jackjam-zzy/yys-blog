package com.jalickli.yys.mapper;

import com.jalickli.yys.entity.Tag;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface TagMapper {

    @Select("select * from t_tag where id = #{id}")
    Tag getTagById(@Param("id")Long id);

    @Insert("insert into t_tag(name) values (#{name})")
    void saveTag(Tag tag);

    @Select("select * from t_tag")
    List<Tag> listTag();


//    @Select("SELECT a.id,a.name,b.* FROM t_tag a,t_blog b,t_blog_tag c WHERE a.id=c.tag_id;")
//    @Results(id ="tagMap",value = {
//            @Result(id =true,column = "id",property = "id"),
//            @Result(column = "name",property = "name"),
//            @Result(property = "blogList",column = "id.",many = @Many(select = "com.jalickli.yys.mapper.BlogMapper.getBlogByTagId" ,fetchType = FetchType.LAZY))
//    })
    List<Tag> getTagInfo();


    @Select("select * from t_blog_tag tbt,t_tag tt where tbt.blog_id=#{id} and tt.id=tbt.tag_id ")
    List<Tag> getTagByBlogId(@Param("id") Long id);

    @Update("update t_tag set name=#{tag.name} where id=#{id}")
    void updateTag(@Param("tag") Tag tag,@Param("id") Long id);

    @Delete("delete from t_tag where id=#{id}")
    void deleteTag(@Param("id") Long id);

    @Select("select * from t_tag where name = #{name}")
    Tag getTagByName(@Param("name") String name);

}
