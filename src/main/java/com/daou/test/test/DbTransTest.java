package com.daou.test.test;

import com.daou.test.db.entity.User;
import com.daou.test.db.repository.UserRedisRepository;
import com.daou.test.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class DbTransTest {
    private final RedisTemplate<String, Object> redisTemplate;

    private final UserRedisRepository userRedisRepository;
    private final UserRepository userRepository;
    private final TimeCheck timeCheck;

    @Transactional
    public long redisTest(long len){
        timeCheck.checkBefore();
        for(long i=1; i<=len; i++){
            redisTemplate.opsForHash().put("user",String.valueOf(i),String.valueOf(LocalDateTime.now()));
        }
        timeCheck.checkAfter();
        return timeCheck.afterTime-timeCheck.beforeTime;
    }
    @Transactional
    public long mysqlTest(long len){
        timeCheck.checkBefore();
        for(long i=1; i<=len; i++){
            User user = User.builder().id(i).localDateTime(LocalDateTime.now()).build();
            userRepository.saveAndFlush(user);
        }
        timeCheck.checkAfter();
        return timeCheck.afterTime-timeCheck.beforeTime;
    }

    public void resetDB(){
        long len = redisTemplate.opsForHash().size("user");
        for(long i=1; i<=len; i++){
            redisTemplate.opsForHash().delete("user",String.valueOf(i));
        }
        userRepository.deleteAll();
    }
}
