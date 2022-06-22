package com.daou.test.controller;

import com.daou.test.result.TimeResult;
import com.daou.test.test.DbTest;
import com.daou.test.test.DbTransTest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class TestController {
    private final DbTest dbTest;

    private final DbTransTest dbTransTest;
    private final TimeResult timeResult;

    @GetMapping
    public String test() {
        return "연결 성공";
    }

    // 기본 Insert Service
    @PostMapping
    public ResponseEntity<String> saveTest(@RequestParam("len") long len) {
        dbTransTest.resetDB();
        System.out.println("삽입 개수 : " + len + "\n");
        for (int i = 0; i < 10; i++) {
            dbTest.resetDB();
            long redisTime = dbTest.redisInsertTest(len);
            long jpaTime = dbTest.mysqlInsertTest(len);
            timeResult.getRedisTime().add(redisTime);
            timeResult.getMysqlTime().add(jpaTime);
        }
        double[] result = timeResult.getAvg();
        System.out.print("Redis 시간 : ");
        for (long num : timeResult.getRedisTime()) {
            System.out.print(num + " ");
        }

        System.out.print("\nMySQL 시간 : ");
        for (long num : timeResult.getMysqlTime()) {
            System.out.print(num + " ");
        }
        timeResult.getMysqlTime().clear();
        timeResult.getRedisTime().clear();
        return new ResponseEntity<>(len + "개 Insert \n" +
                "Redis 소모시간 : " + result[0] + "\nMySQL 소모시간 : " + result[1], HttpStatus.OK);
    }

    // Transaction 적용한 Service
    @PostMapping("/transaction")
    public ResponseEntity<String> insertTransactionTest(@RequestParam("len") long len) {
        dbTest.resetDB();
        System.out.println("삽입 개수 : " + len + "\n");
        for (int i = 0; i < 10; i++) {
            dbTransTest.resetDB();
            long redisTime = dbTransTest.redisTest(len);
            long mysqlTime = dbTransTest.mysqlTest(len);
            timeResult.getRedisTime().add(redisTime);
            timeResult.getMysqlTime().add(mysqlTime);
        }
        double[] result = timeResult.getAvg();
        System.out.print("Redis 시간 : ");
        for (long num : timeResult.getRedisTime()) {
            System.out.print(num + " ");
        }

        System.out.print("\nMySQL 시간 : ");
        for (long num : timeResult.getMysqlTime()) {
            System.out.print(num + " ");
        }
        timeResult.getMysqlTime().clear();
        timeResult.getRedisTime().clear();
        return new ResponseEntity<>(len + "개 Insert \n" +
                "Redis 소모시간 : " + result[0] + "\nMySQL 소모시간 : " + result[1], HttpStatus.OK);
    }
}
