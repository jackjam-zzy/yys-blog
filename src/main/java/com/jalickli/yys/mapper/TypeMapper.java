package com.jalickli.yys.mapper;

import com.jalickli.yys.entity.Type;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface TypeMapper {

    /*
    * 查询所有分类信息，并且包含所有的博客信息
    * */
    @Select("select * from t_type")
    @Results(id="typeMap",value = {
            @Result(id=true,column = "id",property = "id"),  //column为数据库中id字段，property为对应实体类属性
            @Result(column = "name",property = "name"),
            @Result(property = "blogList",column = "id" ,
                    many=@Many(select = "com.jalickli.yys.mapper.BlogMapper.findBlogByTypeId",
                    fetchType = FetchType.LAZY))
    })
    List<Type> listType();

    @Select("select * from t_type where id = #{id}")
    Type getTypeById(@Param("id") Long id);


    @Insert("insert into t_type(name) values (#{name})")
    void saveType(Type type);

    @Update("update t_type set name=#{type.name} where id=#{id}")
    void updateType(@Param("type")Type type,@Param("id") Long id);

    @Delete("delete from t_type where id=#{id}")
    void deleteType(@Param("id") Long id);
}
