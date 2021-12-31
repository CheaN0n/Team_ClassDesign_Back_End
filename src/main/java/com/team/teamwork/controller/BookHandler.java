package com.team.teamwork.controller;

import com.team.teamwork.entity.Book;
import com.team.teamwork.entity.Borrowrecord;
import com.team.teamwork.entity.User;
import com.team.teamwork.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookHandler {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/findAll")//管理员书库管理
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    @GetMapping("/findById/{id}")//书库管理修改书信息
    public Book findbyid(@PathVariable("id") String id){
        Book book = bookRepository.findById(id).get();
        return book;
    }
    //寻找目前可借阅的图书(flag == 1)
    @GetMapping("/findAvaliable")
    public List<Book> findAvaliable() {
        List<Book> avabooks = new ArrayList<Book>();
        List<Book> allbooks = bookRepository.findAll();
        for (Book book : allbooks){
            if (book.getFlag() == 1){
                avabooks.add(book);
            }
        }
        return avabooks;
    }
    @PostMapping("/lendout/{id}")//
    public String lendout(@PathVariable("id") String id){
        Book book = bookRepository.findById(id).get();
        book.setFlag(0);
        Book result = bookRepository.save(book);
        if (result != null){
            return "success";
        }else{
            return "error";
        }
    }
    @PostMapping("/freebook/{id}")
    public void freebook(@PathVariable("id") String id){
        Book book = bookRepository.findById(id).get();
        book.setFlag(1);
        bookRepository.save(book);
    }
    @DeleteMapping("/deletebook/{id}")
    public String deletebook(@PathVariable("id") String id){
        String result = "success";
        try {
        bookRepository.deleteById(id);
        }catch (Exception e){
            result = "error";
        }finally {
            return result;
        }
    }
    @PostMapping("/update")
    public String update(@RequestBody Book book){
        String result = "success";
        try {
            bookRepository.save(book);
        }catch (Exception e){
            result = "error";
        }finally {
            return result;
        }

    }
    @PostMapping("/add")
    public String add(@RequestBody Book book){
        String result = "success";
        book.setFlag(1);
        try {
            bookRepository.save(book);
        }catch (Exception e){
            result = "error";
        }finally {
            return result;
        }
    }
}
