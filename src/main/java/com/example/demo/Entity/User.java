package com.example.demo.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    private String username; //昵称
    private String password;
    private String email;

}
