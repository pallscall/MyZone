package com.example.demo.Mapper;

import com.example.demo.Entity.UserDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDetailMapper {
    @Select("select * from userdetails where email = #{email}")
    UserDetail select(String email);

    @Insert("insert into userdetails(nickName,name,sex,birth,email,resume,headPic) values(#{nickName},#{name},#{sex},#{birth},#{email},#{resume},#{headPic})")
    void insert(UserDetail userDetail);
}
