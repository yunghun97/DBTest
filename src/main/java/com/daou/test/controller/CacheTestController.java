package com.daou.test.controller;

import com.daou.test.db.dto.BoardDto;
import com.daou.test.service.CacheTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test/cache")
@RequiredArgsConstructor
public class CacheTestController {


    private final CacheTestService cacheTestService;

    @GetMapping("/no")
    public BoardDto noCache(@RequestParam long id){
        return cacheTestService.getInfo(id);
    }

    @GetMapping
    @Cacheable(key = "#id", value = "BoardDto", cacheManager = "redisCacheManager")
    public BoardDto getInfo(@RequestParam long id){
        System.out.println("컨트롤 실행 "+ LocalDateTime.now());
        return cacheTestService.getInfo(id);
    }

    @PostMapping
    public ResponseEntity<?> postInfo(@RequestBody BoardDto boardDto){
        return cacheTestService.postInfo(boardDto);
    }
    @PatchMapping
    @CachePut(key= "#id", value = "BoardDto")
    public BoardDto patchInfo(@RequestParam long id, @RequestBody BoardDto boardDto){
        return cacheTestService.patchInfo(id, boardDto);
    }
    @DeleteMapping
    @CacheEvict(key="#id", value = "BoardDto")
    public ResponseEntity<?> deleteInfo(@RequestParam long id){
        return cacheTestService.deleteInfo(id);
    }
}
