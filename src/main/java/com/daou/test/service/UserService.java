package com.daou.test.service;

import com.daou.test.db.entity.UserRedis;
import com.daou.test.db.repository.UserRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping
@RequiredArgsConstructor
public class UserService {
    private final UserRedisRepository userRedisRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public void save(){
        UserRedis userRedis = new UserRedis();
    }

}
