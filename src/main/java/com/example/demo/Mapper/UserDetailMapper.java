package com.example.demo.Mapper;

import com.example.demo.Entity.UserDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDetailMapper {
    @Select("select * from userdetails where email = #{email}")
    UserDetail select(String email);

    @Insert("insert into userdetails(nickName,name,sex,birth,email,resume,headPic) values(#{nickName},#{name},#{sex},#{birth},#{email},#{resume},#{headPic})")
    void insert(UserDetail userDetail);

    @Update("update userdetails set nickName=#{nickName},name=#{name},sex=#{sex},birth=#{birth},resume=#{resume},headPic=#{headPic} where email=#{email}")
    void update(UserDetail userDetail);
}
