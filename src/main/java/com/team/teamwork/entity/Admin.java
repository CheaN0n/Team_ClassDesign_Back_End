package com.team.teamwork.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Admin {
    @Id
    //用户名
    private String username;
    //密码
    private String password;
}
