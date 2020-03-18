package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Entity.UserDetail;
import com.example.demo.Mapper.UserDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
}
