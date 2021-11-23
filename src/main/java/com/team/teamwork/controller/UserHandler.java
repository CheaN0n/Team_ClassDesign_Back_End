package com.team.teamwork.controller;

import com.team.teamwork.entity.User;
import com.team.teamwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/save")
    public String save(@RequestBody User u){
        User result = userRepository.save(u);
        if (result != null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/login/{id}")
    public String login(@PathVariable("id") String username){
        String result = "error";
        try {
            result = userRepository.findById(username).get().getPassword();
        }catch (Exception e){

        }finally {
            return result;
        }
    }
}
