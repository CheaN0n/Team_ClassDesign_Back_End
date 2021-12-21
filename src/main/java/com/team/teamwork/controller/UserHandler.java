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
    public String save(@RequestBody User user){
        user.setDisobeycount(0);
        User result = userRepository.save(user);
        if (result != null){
            return "success";
        }else{
            return "error";
        }
    }

    @PostMapping("/changepassword")
    public String changepassword(@RequestBody User user){
        User targetuser = userRepository.findById(user.getUsername()).get();
        targetuser.setPassword(user.getPassword());
        User result = userRepository.save(targetuser);
        if (result != null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/login/{id}")
    public String login(@PathVariable("id") String username){
        String result = "error";
        result = userRepository.findById(username).get().getPassword();
        return result;

    }
    @GetMapping("/getname/{username}")
    public String getname(@PathVariable("username") String username){
        String result = "error";
        result = userRepository.findById(username).get().getName();
        return result;

    }
    @GetMapping("/getpass/{username}")
    public String getpassword(@PathVariable("username") String username){
        String result = "error";
        result = userRepository.findById(username).get().getPassword();
        return result;

    }
    @GetMapping("/getdetails/{username}")
    public User getinfo(@PathVariable("username") String username){
        User targetuser = userRepository.findById(username).get();
        targetuser.setPassword("");//提高安全性，将密码设为空后再进行传输
        return targetuser;
    }

    @PostMapping("/addcount/{username}")
    public void adddisobeycount(@PathVariable("username") String username){
        User disobeyuser = userRepository.findById(username).get();
        int pre = disobeyuser.getDisobeycount();
        disobeyuser.setDisobeycount(++pre);
        userRepository.save(disobeyuser);
    }
    @PostMapping("/saveinfo")
    public String saveinfo(@RequestBody User user){
        User targetuser = userRepository.findById(user.getUsername()).get();
        targetuser.setBirthday(user.getBirthday());
        targetuser.setEmail(user.getEmail());
        targetuser.setTel(user.getTel());
        User result = userRepository.save(targetuser);
        if (result != null){
            return "success";
        }else{
            return "error";
        }
    }
}
