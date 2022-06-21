package com.daou.test.test;

import com.daou.test.db.entity.User;
import com.daou.test.db.entity.UserRedis;
import com.daou.test.db.repository.UserRedisRepository;
import com.daou.test.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DbTest {
    private final UserRedisRepository userRedisRepository;
    private final UserRepository userRepository;

    private final TimeCheck timeCheck;

    @Transactional
    public void redisInsertTest(long len){
        timeCheck.checkBefore();
        for(int i=1; i<=len; i++){
            UserRedis userRedis = new UserRedis();
            userRedis.setId(i);
            userRedis.setLocalDateTime(LocalDateTime.now());
            userRedisRepository.save(userRedis);
        }
        System.out.print("Redis : ");
        timeCheck.checkAfter();
    }
    @Transactional
    public void JPAInsertTest(long len){
        timeCheck.checkBefore();
        for(int i=1; i<=len; i++){
            User user = new User();
            user.setId(i);
            user.setLocalDateTime(LocalDateTime.now());
            userRepository.save(user);
        }
        System.out.print("JPA : ");
        timeCheck.checkAfter();
        this.resetDB();
    }

    public void resetDB(){
        userRedisRepository.deleteAll();
        userRepository.deleteAll();
    }
}
