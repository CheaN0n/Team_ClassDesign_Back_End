package com.team.teamwork.controller;

import com.team.teamwork.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminHandler {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/login/{id}")
    public String login(@PathVariable("id") String username){
        String result = "error";
        try{
            result = adminRepository.findById(username).get().getPassword();
        }catch(Exception e){
        }finally {
            return result;
        }
    }

}
