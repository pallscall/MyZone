package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Entity.UserDetail;
import com.example.demo.Mapper.UserDetailMapper;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;


@Controller
public class LoginController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDetailMapper userDetailMapper;
    @Autowired
    private MailService mailService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam(name="name",required = false) String name,
                           @RequestParam(name="mail",required = false) String mail,
                           @RequestParam(name="password",required = false) String password,
                           @RequestParam(name="vcode",required = false) String vcode,
                           HttpServletRequest request,
                           Model model){
        String checkCode = (String) request.getSession().getAttribute("vcode");
        if(vcode.equals(checkCode)){ //注册成功
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setUsername(name);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setEmail(mail);
            UserDetail userDetail = new UserDetail();
            userDetail.setNickName(name);
            userDetail.setEmail(mail);
            userDetail.setHeadPic("/layui/images/login.jpg"); //设置一个默认的头像
            userDetail.setName(" ");
            userDetail.setSex(" ");
            userDetail.setBirth(" ");
            userDetail.setResume(" ");
            userMapper.insert(user);
            userDetailMapper.insert(userDetail);
            return "login";
        }else{
            model.addAttribute("errormsg","验证码错误");
        }
        return "register";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam(name="email") String email,
                       @RequestParam(name= "password") String password,
                       HttpSession session,
                       Model model){
        User user = userMapper.getUserlist(email);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(user == null){
            model.addAttribute("errormsg","该用户未注册！");
        }
        else if(bCryptPasswordEncoder.matches(password,user.getPassword())){
            session.setAttribute("user",user);
            return "redirect:/";
        }else{
            model.addAttribute("errormsg","邮箱或密码错误！");
        }
        return "login";

    }
    /**
     * 获取验证码
     * @param email
     * @return
     */
    @RequestMapping("/getCheckCode")
    @ResponseBody
    public String getCheckCode(String email, HttpServletRequest request) {
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        System.out.println(checkCode);
        request.getSession().setAttribute("vcode",checkCode);
        String message = "您的注册验证码为：" + checkCode;
        try {
            mailService.sendVertifyCode(email, "注册验证码", message);
        } catch (Exception e) {
            return "";
        }
        return checkCode;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
        return "login";
    }
}
