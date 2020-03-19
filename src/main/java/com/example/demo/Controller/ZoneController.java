package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Entity.UserDetail;
import com.example.demo.Mapper.UserDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ZoneController {

    @Autowired
    private UserDetailMapper userDetailMapper;
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "index";
    }

    @RequestMapping("/user/details")
    public String userDetails(Model model, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        UserDetail userDetail = userDetailMapper.select(user.getEmail());
        model.addAttribute("userDetail",userDetail);
        return "userdetails";
    }

    @PostMapping("/user/modify")
    @ResponseBody
    public String userModify(@RequestBody UserDetail userd){
        UserDetail userDetail = new UserDetail();
        System.out.println(userd.getEmail().replace("%40","@"));
        userDetail.setEmail(userd.getEmail().replace("%40","@"));
        userDetail.setResume(userd.getResume());
        userDetail.setBirth(userd.getBirth());
        userDetail.setName(userd.getName());
        userDetail.setNickName(userd.getNickName());
        userDetail.setSex(userd.getSex());
        userDetail.setHeadPic(userd.getHeadPic());
        userDetailMapper.update(userDetail);
        return "修改成功";
    }
}
