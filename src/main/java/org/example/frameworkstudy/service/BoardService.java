package org.example.frameworkstudy.service;

import org.example.frameworkstudy.dto.BoardCreateDTO;
import org.example.frameworkstudy.dto.BoardReadDTO;
import org.example.frameworkstudy.dto.BoardUpdateDTO;

import java.util.List;

public interface BoardService {

    BoardCreateDTO writeBoard(BoardCreateDTO board);

    List<BoardReadDTO> listBoard();

    BoardReadDTO viewBoard(int boardId);

    BoardUpdateDTO updateBoard(int boardId, BoardUpdateDTO board);
}
