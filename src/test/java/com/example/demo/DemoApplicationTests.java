package com.example.demo;

import com.example.demo.Service.MailService;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
class DemoApplicationTests {

    @Autowired
    private MailService mailService;
    @Test
    void contextLoads() {
    }

    @Test
    void Test1(){
        String to = "306698601@qq.com";
        String title="test";
        String context = "6379";
        mailService.sendVertifyCode(to,title,context);
    }

}
