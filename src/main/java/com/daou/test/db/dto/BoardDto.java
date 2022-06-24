package com.daou.test.db.dto;

import com.daou.test.db.entity.Board;
import com.daou.test.db.entity.UserRedis;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private long id;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    public static BoardDto convert(Board board){
        if(board!=null){
            return BoardDto.builder().id(board.getId()).title(board.getTitle()).content(board.getContent()).createdTime(board.getCreatedTime()).build();
        }
        return null;
    }
}
