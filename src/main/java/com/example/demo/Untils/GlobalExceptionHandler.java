package com.example.demo.Untils;

import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object ExceptionHandler(Exception e){
        e.printStackTrace();
        return "您访问的页面正忙，请稍后再访问";
    }

//    @Bean
//    public WebServerFactoryCustomizer createWebServerFactoryCustomizer(){
//        WebServerFactoryCustomizer webServerFactoryCustomizer = new WebServerFactoryCustomizer() {
//            @Override
//            public void customize(WebServerFactory factory) {
//               factory.add
//            }
//        };
//        return webServerFactoryCustomizer;
//    }
}
