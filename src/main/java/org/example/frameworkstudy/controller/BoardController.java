package org.example.frameworkstudy.controller;

import org.example.frameworkstudy.dto.BoardCreateDTO;
import org.example.frameworkstudy.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
