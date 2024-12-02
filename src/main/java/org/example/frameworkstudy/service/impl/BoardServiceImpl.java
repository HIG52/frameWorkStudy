package org.example.frameworkstudy.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworkstudy.dto.BoardCreateDTO;
import org.example.frameworkstudy.dto.BoardDeleteDTO;
import org.example.frameworkstudy.dto.BoardReadDTO;
import org.example.frameworkstudy.dto.BoardUpdateDTO;
import org.example.frameworkstudy.entity.Boards;
import org.example.frameworkstudy.repository.BoardRepository;
import org.example.frameworkstudy.service.BoardService;
import org.example.frameworkstudy.service.ViewCountService;
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

    private final ViewCountService viewCountService;

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
            BoardReadDTO boardReadDTO = this.boardRepository.findById(boardId)
                    .map(board -> new BoardReadDTO(
                            board.getBoardId(),
                            board.getTitle(),
                            board.getContents(),
                            board.getAuthor(),
                            board.getModifiedAt()
                    ))
                    .orElseThrow(() -> new RuntimeException(boardId + " 번호에 맞는 게시글을 찾을수 없습니다."));
            // TODO : 조회수 로직 추가 예정
            viewCountService.incrementViewCount(boardId);
            return boardReadDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
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

    @Override
    public BoardDeleteDTO deleteBoard(int boardId) {
        // 삭제 대상 조회
        Boards board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("해당 게시판을 찾을수 없습니다."));

        // 필요한 데이터를 DTO로 변환
        BoardDeleteDTO boardDeleteDTO = new BoardDeleteDTO(board.getBoardId());

        // 삭제 수행
        boardRepository.deleteById(boardId);

        // 삭제된 데이터 반환
        return boardDeleteDTO;
    }

}
