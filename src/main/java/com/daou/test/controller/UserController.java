package com.daou.test.controller;

import com.daou.test.test.DbTest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final DbTest dbTest;

    @GetMapping
    public String test(){
        return "연결 성공";
    }

    @PostMapping
    public ResponseEntity<String> saveTest(@RequestParam("len") long len){
        System.out.println("len 값 : "+len);
        dbTest.redisInsertTest(len);
        dbTest.JPAInsertTest(len);
        return new ResponseEntity<String>("안녕하세욤", HttpStatus.OK);
    }

    @DeleteMapping
    public void resetDB(){
        dbTest.resetDB();
    }
}
