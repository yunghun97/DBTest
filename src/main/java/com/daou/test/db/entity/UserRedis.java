package com.daou.test.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.LocalDateTime;

@RedisHash("user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRedis{
    @Id
    private long id;
    private String name;
    private LocalDateTime createdTime;
}
