package com.daou.test.service;

import com.daou.test.db.dto.UserDto;
import com.daou.test.db.entity.User;
import com.daou.test.db.entity.UserRedis;
import com.daou.test.db.repository.UserRedisRepository;
import com.daou.test.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TestService {

    private final UserRepository userRepository;
    private final UserRedisRepository userRedisRepository;

    public ResponseEntity<?> getMysql(long id){
        User user = userRepository.findById(id).orElse(null);
        if(nullCheck(user)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            UserDto userDto = UserDto.convert(user);
            return new ResponseEntity<>(userDto,HttpStatus.OK);
        }

    }
    public ResponseEntity<?> getRedis(long id) {
        UserRedis user = userRedisRepository.findById(id).orElse(null);
        if(nullCheck(user)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            UserDto userDto = UserDto.convert(user);
            return new ResponseEntity<>(userDto,HttpStatus.OK);
        }

    }
    public User postMysql(long id){
        User user = User.builder().name("유저"+id).build();
        return userRepository.save(user);

    }
    public UserRedis postRedis(long id){
        UserRedis userRedis = new UserRedis(id,"유저"+id, LocalDateTime.now());
        return userRedisRepository.save(userRedis);

    }
    public boolean nullCheck(Object obj){
        return obj == null;
    }
}
