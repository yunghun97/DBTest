package com.daou.test.service;

import com.daou.test.db.dto.BoardDto;
import com.daou.test.db.entity.Board;
import com.daou.test.db.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CacheTestService {

    private final BoardRepository boardRepository;

    public ResponseEntity<?> getInfo(long id){
        Board board = boardRepository.findById(id).orElse(null);
        if(nullCheck(board)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        BoardDto boardDto = BoardDto.convert(board);
        return new ResponseEntity<>(boardDto,HttpStatus.OK);
    }
    public ResponseEntity<?> postInfo(long id, BoardDto boardDto){
        Board board = Board.builder().id(id).title(boardDto.getTitle()).content(boardDto.getContent()).createdTime(LocalDateTime.now()).build();
        BoardDto resultBoardDto = BoardDto.convert(boardRepository.save(board));
        if(nullCheck(resultBoardDto)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(resultBoardDto,HttpStatus.OK);
    }
    public boolean nullCheck(Object obj){
        return obj == null;
    }
}
