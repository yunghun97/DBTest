package com.daou.test.service;

import com.daou.test.db.dto.BoardDto;
import com.daou.test.db.entity.Board;
import com.daou.test.db.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CacheTestService {

    private final BoardRepository boardRepository;

    public BoardDto getInfo(long id){
        Board board = boardRepository.findById(id).orElse(null);
        System.out.println("서비스단 실행 "+ LocalDateTime.now());
        if(nullCheck(board)) return null;
        return BoardDto.convert(board);
    }
    public ResponseEntity<?> postInfo(BoardDto boardDto){
        Board board = Board.builder().title(boardDto.getTitle()).content(boardDto.getContent()).build();
        BoardDto resultBoardDto = BoardDto.convert(boardRepository.save(board));
        if(nullCheck(resultBoardDto)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(resultBoardDto,HttpStatus.OK);
    }
    public BoardDto patchInfo(long id, BoardDto boardDto) {
        Optional<Board> oBoard = boardRepository.findById(id);
        Board board = null;
        BoardDto result;
        if(oBoard.isPresent()){
            board = oBoard.get();
            board.setTitle(boardDto.getTitle());
            board.setContent(boardDto.getContent());
            board = boardRepository.save(board);
        }
        return BoardDto.builder().id(board.getId()).title(board.getTitle()).content(board.getContent()).build();

    }
    public boolean nullCheck(Object obj){
        return obj == null;
    }

    public ResponseEntity<?> deleteInfo(long id) {
        boardRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
