package com.team.teamwork.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Borrowrecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //序号仅用来进行操作，前端不显示
    private Integer id;
    //进行借阅的用户的用户名
    private String username;
    //进行借阅的用户的用户名
    private String name;
    //被借阅的书序号
    private String bookid;
    //被借阅的书名
    private String bookname;
    //被借阅的书作者
    private String author;
    //被借阅的书出版社
    private String publisher;
    //被借阅的书页数
    private Integer page;
    //被借阅的书的最晚归还日期
    private String ddl;
    //状态字：0代表借阅中，1代表已经申请归还
    private Integer flag;

}
