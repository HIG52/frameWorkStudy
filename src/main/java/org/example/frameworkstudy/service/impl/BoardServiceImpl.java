package org.example.frameworkstudy.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworkstudy.dto.BoardCreateDTO;
import org.example.frameworkstudy.dto.BoardReadDTO;
import org.example.frameworkstudy.dto.BoardUpdateDTO;
import org.example.frameworkstudy.entity.Boards;
import org.example.frameworkstudy.repository.BoardRepository;
import org.example.frameworkstudy.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            Boards writeBoard = this.boardRepository.save(boards);

            return new BoardCreateDTO(true, writeBoard.getBoardId());
        }catch(Exception e){
            return new BoardCreateDTO(false, null);
        }
    }

    @Override
    public List<BoardReadDTO> listBoard() {
        List<Boards> listBoard = this.boardRepository.findAll();
        return listBoard.stream()
                .map(board -> new BoardReadDTO(
                        board.getBoardId(),
                        board.getTitle(),
                        board.getAuthor(),
                        board.getModifiedAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public BoardReadDTO viewBoard(int boardId) {
        try{
            return this.boardRepository.findById(boardId)
                    .map(board -> new BoardReadDTO(
                            board.getBoardId(),
                            board.getTitle(),
                            board.getContents(),
                            board.getAuthor(),
                            board.getModifiedAt()
                    ))
                    .orElseThrow(() -> new RuntimeException(boardId + " 번호에 맞는 게시글을 찾을수 없습니다."));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public BoardUpdateDTO updateBoard(int boardId, BoardUpdateDTO boardUpdateDTO) {
        // 엔티티를 업데이트
        Boards updatedBoard = boardRepository.findById(boardId).map(existingBoard -> {
            existingBoard.changeContent(boardUpdateDTO.getContents());
            existingBoard.changeTitle(boardUpdateDTO.getTitle());
            return existingBoard; // 변경된 엔티티 반환
        }).orElseThrow(() -> new RuntimeException("Board not found with id: " + boardId));

        // 변경된 엔티티를 기반으로 DTO 생성
        return new BoardUpdateDTO(updatedBoard.getBoardId(), updatedBoard.getTitle(), updatedBoard.getContents(), updatedBoard.getAuthor(), updatedBoard.getModifiedAt());
    }

}