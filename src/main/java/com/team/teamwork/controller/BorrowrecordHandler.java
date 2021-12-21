package com.team.teamwork.controller;

import com.team.teamwork.entity.Book;
import com.team.teamwork.entity.Borrowrecord;
import com.team.teamwork.repository.BorrowrecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/borrowrecord")
public class BorrowrecordHandler {
    @Autowired
    private BorrowrecordRepository borrowrecordRepository;

    @PostMapping("/lendout")
    public String lendout(@RequestBody Borrowrecord record) {
        record.setDdl(record.getDdl().substring(0,10));
        Borrowrecord result = borrowrecordRepository.save(record);
        if (result != null){
            return "success";
        }else{
            return "error";
        }
    }
    @GetMapping("/getuserbooklist/{username}")
    public List<Borrowrecord> getuserbooklist(@PathVariable("username") String username) {
        List<Borrowrecord> userlist = new ArrayList<Borrowrecord>();
        List<Borrowrecord> all = borrowrecordRepository.findAll();
        for (Borrowrecord br : all){
            if (br.getUsername().equals(username)){
                userlist.add(br);
            }
        }
        return userlist;
    }
    @PostMapping("/apply/{id}")
    public String returnapply(@PathVariable("id") Integer id) throws ParseException {
        Borrowrecord br = borrowrecordRepository.findById(id).get();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(br.getDdl());
        Date date2 = new Date();
        if (date2.after(date1)){
            return "outdate";
        }

        if (br.getFlag()==1){
            return "repeat";
        }
        br.setFlag(1);//将其状态改为正在申请归还，管理员端可查看所有申请归还的书籍
        Borrowrecord result = borrowrecordRepository.save(br);
        if (result != null){
            return "success";
        }else{
            return "error";
        }
    }
}
