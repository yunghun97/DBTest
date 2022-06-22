package com.daou.test.test;

import com.daou.test.db.entity.User;
import com.daou.test.db.entity.UserRedis;
import com.daou.test.db.repository.UserRedisRepository;
import com.daou.test.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DbTest {
    private final UserRedisRepository userRedisRepository;
    private final UserRepository userRepository;

    private final TimeCheck timeCheck;

    public Long redisInsertTest(long len){
        timeCheck.checkBefore();
        for(int i=1; i<=len; i++){
            UserRedis userRedis = new UserRedis();
            userRedis.setId(i);
            userRedis.setLocalDateTime(LocalDateTime.now());
            userRedisRepository.save(userRedis);
        }
        timeCheck.checkAfter();
        return timeCheck.getResultTime();
    }
    public Long mysqlInsertTest(long len){
        timeCheck.checkBefore();
        for(int i=1; i<=len; i++){
            User user = new User();
            user.setId(i);
            user.setLocalDateTime(LocalDateTime.now());
            userRepository.saveAndFlush(user);
        }
        timeCheck.checkAfter();
        return timeCheck.getResultTime();
    }

    public void resetDB(){
        userRedisRepository.deleteAll();
        userRepository.deleteAll();
    }
}
