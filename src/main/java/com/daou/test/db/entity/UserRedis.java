package com.daou.test.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.LocalDateTime;

@RedisHash("user")
@Getter
@Setter
public class UserRedis {
    @Id
    private long id;
    private LocalDateTime localDateTime;
}
