package com.example.demo.Entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username; //昵称
    private String password;
    private String email;

}
