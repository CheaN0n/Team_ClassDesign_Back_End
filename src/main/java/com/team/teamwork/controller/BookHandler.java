package com.team.teamwork.controller;

import com.team.teamwork.entity.Book;
import com.team.teamwork.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookHandler {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/findAll")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}