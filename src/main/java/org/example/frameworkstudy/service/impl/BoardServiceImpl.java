package org.example.frameworkstudy.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworkstudy.dto.BoardCreateDTO;
import org.example.frameworkstudy.entity.Boards;
import org.example.frameworkstudy.repository.BoardRepository;
import org.example.frameworkstudy.service.BoardService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public BoardCreateDTO writeBoard(BoardCreateDTO board) {

        Boards boards = Boards.builder()
                .author(board.getAuthor())
                .title(board.getTitle())
                .contents(board.getContents())
                .build();

        try{
            Boards writeBoard = boardRepository.save(boards);

            return new BoardCreateDTO(true, writeBoard.getBoardId());
        }catch(Exception e){
            return new BoardCreateDTO(false, null);
        }
    }

}
