package com.jalickli.yys.mapper;

import com.jalickli.yys.entity.Blog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface BlogMapper {
    /*
   * 查
   * */
    /*
    * 根据分类id查询博客信息
    * */
    @Select("select * from t_blog where type_id =#{typeId}")
    List<Blog> findBlogByTypeId(@Param("typeId") Long typeId);

    /*
    * 查询所有博客信息，包括分类信息
    * */
    @Select("select * from t_blog")
    @Results(id ="adminBlogsMap",value = {
            @Result(id =true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "description",property = "description"),
            @Result(column = "appreciate",property = "appreciate"),
            @Result(column = "comment",property = "comment"),
            @Result(column = "copyright_opening",property = "copyrightOpening"),
            @Result(column = "published",property = "published"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "content",property = "content"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "flag",property = "flag"),
            @Result(column = "picture",property = "picture"),
            @Result(column = "view",property = "view"),
            @Result(column = "modify_time",property = "modifyTime"),
            @Result(property = "type",column = "type_id",one=@One(select="com.jalickli.yys.mapper.TypeMapper.getTypeById",fetchType = FetchType.EAGER)),  //用type_id字段查
            @Result(property = "user",column = "user_id", one=@One(select = "com.jalickli.yys.mapper.UserMapper.findUserById",fetchType = FetchType.EAGER)),  //用user_id字段查
            @Result(property = "tagList",column = "id",many = @Many(select = "com.jalickli.yys.mapper.TagMapper.getTagByBlogId" ,fetchType = FetchType.LAZY))
    })
    List<Blog> listBlogAll();

    @Select("select * from t_blog where published=#{published}")
    @Results(id ="indexBlogsMap",value = {
            @Result(id =true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "description",property = "description"),
            @Result(column = "appreciate",property = "appreciate"),
            @Result(column = "comment",property = "comment"),
            @Result(column = "copyright_opening",property = "copyrightOpening"),
            @Result(column = "published",property = "published"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "content",property = "content"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "flag",property = "flag"),
            @Result(column = "picture",property = "picture"),
            @Result(column = "view",property = "view"),
            @Result(column = "modify_time",property = "modifyTime"),
            @Result(property = "type",column = "type_id",one=@One(select="com.jalickli.yys.mapper.TypeMapper.getTypeById",fetchType = FetchType.EAGER)),  //用type_id字段查
            @Result(property = "user",column = "user_id", one=@One(select = "com.jalickli.yys.mapper.UserMapper.findUserById",fetchType = FetchType.EAGER)),  //用user_id字段查
            @Result(property = "tagList",column = "id",many = @Many(select = "com.jalickli.yys.mapper.TagMapper.getTagByBlogId" ,fetchType = FetchType.LAZY))
    })
    List<Blog> listBlogWherePublish(Boolean published);

    @Select("select * from t_blog where id=#{id}")
    @Results(id ="blogMap",value = {
            @Result(id =true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "description",property = "description"),
            @Result(column = "appreciate",property = "appreciate"),
            @Result(column = "comment",property = "comment"),
            @Result(column = "copyright_opening",property = "copyrightOpening"),
            @Result(column = "published",property = "published"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "content",property = "content"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "flag",property = "flag"),
            @Result(column = "picture",property = "picture"),
            @Result(column = "view",property = "view"),
            @Result(column = "modify_time",property = "modifyTime"),
            @Result(property = "type",column = "type_id",one=@One(select="com.jalickli.yys.mapper.TypeMapper.getTypeById",fetchType = FetchType.EAGER)),  //用type_id字段查
            @Result(property = "user",column = "user_id", one=@One(select = "com.jalickli.yys.mapper.UserMapper.findUserById",fetchType = FetchType.EAGER)),  //用user_id字段查
            @Result(property = "tagList",column = "id",many = @Many(select = "com.jalickli.yys.mapper.TagMapper.getTagByBlogId" ,fetchType = FetchType.LAZY))
    })
    Blog getBlogById(@Param("id")Long id);


//
//    @Select("select * from t_blog where id = #{id}")
//    Blog getBlogById(@Param("id") Long id);



    List<Blog> listBlogByForm(Blog blog);

    /**
     * 根据推荐
     * @return
     */
    @Select("select * from t_blog a where a.recommend=true order by modify_time desc")
    List<Blog> findTitleByRecommend();

    /*
    * 增
    * */
    //insert into t_blog(title,appreciate,comment,copyright_opening,published,recommend,content,create_time,flag,picture,view,type_id,user_id,modify_time)values(#{title},#{appreciate},#{comment},#{copyright_opening},#{published},#{recommend},#{content},#{create_time},#{flag},#{picture},#{view},#{blog.type.id},#{blog.user.id},#{modify_time})
//    @Insert("insert into t_blog(title,appreciate,comment,copyright_opening,published,recommend,content,create_time,flag,picture,view,type_id,user_id,modify_time) values ( #{blog.title},#{blog.appreciate},#{blog.comment},#{blog.copyrightOpening},#{blog.published},#{blog.recommend},#{blog.content},#{blog.createTime},#{blog.flag},#{blog.picture},#{blog.view},#{blog.type.id},#{blog.user.id},#{blog.modifyTime})")
    @Insert("insert into t_blog(title,description,appreciate,comment,copyright_opening,published,recommend,content,create_time,flag,picture,view,type_id,user_id,modify_time) values ( #{title},#{description},#{appreciate},#{comment},#{copyrightOpening},#{published},#{recommend},#{content},#{createTime},#{flag},#{picture},#{view},#{type.id},#{user.id},#{modifyTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void saveBlog(Blog blog);

    /**
    *在保存博客的时候在中间表中维护关系
    * */
    @Insert("insert into t_blog_tag(blog_id,tag_id) values(#{b_id},#{t_id})")
    void saveBlogTag(@Param("b_id") Long b_id,@Param("t_id") Long t_id);

    /*
    * 删
    * */
    @Delete("delete from t_blog where id=#{id}")
    void deleteBlog(Long id);

    /*
    *改,根据博客id更新信息
    * */
    @Update("update t_blog set title=#{blog.title},description=#{blog.description},appreciate=#{blog.appreciate},comment=#{blog.comment},copyright_opening=#{blog.copyrightOpening},published=#{blog.published},recommend=#{blog.recommend},content=#{blog.content},flag=#{blog.flag},picture=#{blog.picture},view=#{blog.view},type_id=#{blog.type.id},user_id=#{blog.user.id},modify_time=#{blog.modifyTime} where id=#{blogId}")
    void updateBlog(@Param("blog")Blog blog,@Param("blogId") Long blogId);

    /**
     * 删除博客和标签的联系
     * @param blogId
     */
    @Delete("delete from t_blog_tag where blog_id=#{blogId}")
    void deleteBlogTagRelationship(@Param("blogId") Long blogId);

    /**
     * 新增博客和标签的关系
     * @param blogId
     * @param tagId
     */
    @Insert("insert into t_blog_tag(blog_id,tag_id) values(#{blogId},#{tagId})")
    void insertBlogTagRelationship(@Param("blogId") Long blogId,@Param("tagId") Long tagId);

//    @Select("select * from t_blog a join t_blog_tag b on a.id=b.blog_id where b.tag_id=#{TagId}")
    Blog getBlogByTagId(@Param("TagId")Long TagId);

}
