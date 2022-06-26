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

    public BoardDto getInfo(long id){
        Board board = boardRepository.findById(id).orElse(null);
        System.out.println("서비스단 실행");
        if(nullCheck(board)) return null;
        return BoardDto.convert(board);
    }
    public ResponseEntity<?> postInfo(long id, BoardDto boardDto){
        Board board = Board.builder().id(id).title(boardDto.getTitle()).content(boardDto.getContent()).build();
        BoardDto resultBoardDto = BoardDto.convert(boardRepository.save(board));
        if(nullCheck(resultBoardDto)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(resultBoardDto,HttpStatus.OK);
    }
    public boolean nullCheck(Object obj){
        return obj == null;
    }
}
