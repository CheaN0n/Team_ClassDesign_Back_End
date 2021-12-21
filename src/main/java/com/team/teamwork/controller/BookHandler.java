package com.team.teamwork.controller;

import com.team.teamwork.entity.Book;
import com.team.teamwork.entity.User;
import com.team.teamwork.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookHandler {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/findAll")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    //寻找目前可借阅的图书(flag == 0)
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
    @PostMapping("/lendout/{id}")
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
}
