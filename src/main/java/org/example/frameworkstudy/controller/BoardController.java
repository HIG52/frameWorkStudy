package org.example.frameworkstudy.controller;

import org.example.frameworkstudy.dto.BoardCreateDTO;
import org.example.frameworkstudy.dto.BoardDeleteDTO;
import org.example.frameworkstudy.dto.BoardReadDTO;
import org.example.frameworkstudy.dto.BoardUpdateDTO;
import org.example.frameworkstudy.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //게시글 작성
    @PostMapping("/board")
    public ResponseEntity<BoardCreateDTO> writeBoard(@RequestBody BoardCreateDTO boardCreateDTO) {
        BoardCreateDTO writeBoard = boardService.writeBoard(boardCreateDTO);
        return ResponseEntity.ok(writeBoard);
    }

    //게시글 리스트 조회
    @GetMapping("/boards")
    public ResponseEntity<List<BoardReadDTO>> listBoards() {
        List<BoardReadDTO> boardListDTORead = boardService.listBoard();
        return ResponseEntity.ok(boardListDTORead);
    }

    //게시글 상세 조회
    @GetMapping("/board/{boardId}")
    public ResponseEntity<BoardReadDTO> viewBoard(@PathVariable int boardId) {
        BoardReadDTO viewBoard = boardService.viewBoard(boardId);
        return ResponseEntity.ok(viewBoard);
    }

    //게시글 수정
    @PutMapping("/board/{boardId}")
    public ResponseEntity<BoardUpdateDTO> updateBoard(@PathVariable int boardId, @RequestBody BoardUpdateDTO boardUpdateDTO) {
        BoardUpdateDTO updateBoard = boardService.updateBoard(boardId, boardUpdateDTO);
        return ResponseEntity.ok(updateBoard);
    }

    //게시글 삭제
    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<BoardDeleteDTO> deleteBoard(@PathVariable int boardId) {
        BoardDeleteDTO deleteBoard = boardService.deleteBoard(boardId);
        return ResponseEntity.ok(deleteBoard);
    }



}
