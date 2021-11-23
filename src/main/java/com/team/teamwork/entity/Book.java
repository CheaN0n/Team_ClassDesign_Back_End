package com.team.teamwork.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Book {
    //书序号作为主键
    @Id
    private String id;
    //书名
    private String name;
    //作者
    private String author;
    //出版社
    private String publisher;
    //页数
    private Integer page;
    //状态
    private Integer flag;

}
