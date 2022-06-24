package com.daou.test.controller;

import com.daou.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("test")
public class TestController {
    private final TestService testService;
    @GetMapping("/mysql")
    public ResponseEntity<?> getMysqlTest(@RequestParam("id") long id){

        return new ResponseEntity<>(testService.getMysql(id),HttpStatus.OK);
    }
    //    redis 데이터 가져오기
    @GetMapping("/redis")
    public ResponseEntity<?> getRedisTest(@RequestParam("id") long id){

        return new ResponseEntity<>(testService.getRedis(id),HttpStatus.OK);
    }
    @PostMapping("/mysql")
    public ResponseEntity<?> postMysqlTest(@RequestBody Map<String, Long> map){
        return new ResponseEntity<>(testService.postMysql(map.get("id")), HttpStatus.OK);
    }
    @PostMapping("/redis")
    public ResponseEntity<?> postRedisTest(@RequestBody Map<String, Long> map){
        return new ResponseEntity<>(testService.postRedis(map.get("id")),HttpStatus.OK);
    }
}
