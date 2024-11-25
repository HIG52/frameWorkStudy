package org.example.frameworkstudy.service;

import jakarta.transaction.Transactional;
import org.example.frameworkstudy.dto.BoardCreateDTO;
import org.example.frameworkstudy.dto.BoardDeleteDTO;
import org.example.frameworkstudy.dto.BoardReadDTO;
import org.example.frameworkstudy.dto.BoardUpdateDTO;

import java.util.List;

public interface BoardService {

    @Transactional
    BoardCreateDTO writeBoard(BoardCreateDTO board);

    @Transactional
    List<BoardReadDTO> listBoard();

    @Transactional
    BoardReadDTO viewBoard(int boardId);

    @Transactional
    BoardUpdateDTO updateBoard(int boardId, BoardUpdateDTO board);

    @Transactional
    BoardDeleteDTO deleteBoard(int boardId);
}
