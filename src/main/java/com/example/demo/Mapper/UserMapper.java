package com.example.demo.Mapper;


import com.example.demo.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into user(username, password, email) values(#{username},#{password},#{email})")
    void insert(User user);

    @Select("select * from user where email=#{email}")
    User getUserlist(String email);
}
