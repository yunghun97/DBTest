package com.daou.test.controller;

import com.daou.test.db.dto.BoardDto;
import com.daou.test.service.CacheTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/cache")
@RequiredArgsConstructor
public class CacheTestController {


    private final CacheTestService cacheTestService;

    @GetMapping
    @Cacheable(key = "#id", value = "BoardDto", cacheManager = "redisCacheManager")
    public BoardDto getInfo(@RequestParam long id){
        return cacheTestService.getInfo(id);
    }

    @PostMapping
    @CacheEvict(key = "#id", value = "BoardDto")
    public ResponseEntity<?> postInfo(@RequestParam long id, @RequestBody BoardDto boardDto){
        return cacheTestService.postInfo(id, boardDto);
    }
}
