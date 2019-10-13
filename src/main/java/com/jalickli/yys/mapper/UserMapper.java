package com.jalickli.yys.mapper;

import com.jalickli.yys.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where username = #{username} and password = #{password}")
    User findByUsernameAndPassword(@Param(("username")) String username,@Param("password") String password);

    @Select("select * from t_user where id = #{id}")
    User findUserById(@Param("id") Long id);

}
