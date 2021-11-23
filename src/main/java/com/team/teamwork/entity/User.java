package com.team.teamwork.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    //用户名
    private String username;
    //姓名
    private String name;
    //密码
    private String password;
    //电话号码
    private String tel;
    //电子邮箱
    private String email;
    //生日
    private String birthday;

}
