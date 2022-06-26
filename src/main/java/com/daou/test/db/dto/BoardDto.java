package com.daou.test.db.dto;

import com.daou.test.db.entity.Board;
import com.daou.test.db.entity.UserRedis;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto implements Serializable {
    private long id;
    private String title;
    private String content;
    public static BoardDto convert(Board board){
        if(board!=null){
            return BoardDto.builder().id(board.getId()).title(board.getTitle()).content(board.getContent()).build();
        }
        return null;
    }
}
