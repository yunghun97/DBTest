package com.daou.test.db.dto;

import com.daou.test.db.entity.User;
import com.daou.test.db.entity.UserRedis;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {
    private long id;
    private String name;
    private LocalDateTime createdTime;

    public static UserDto convert(User user){
        if(user!=null){
            return UserDto.builder().id(user.getId()).name(user.getName()).createdTime(user.getCreatedTime()).build();
        }
        return null;
    }
    public static UserDto convert(UserRedis user){
        if(user!=null){
            return UserDto.builder().id(user.getId()).name(user.getName()).createdTime(user.getCreatedTime()).build();
        }
        return null;
    }

}
