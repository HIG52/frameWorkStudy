package org.example.frameworkstudy.service;

import org.example.frameworkstudy.dto.BoardCreateDTO;
import org.example.frameworkstudy.dto.BoardDeleteDTO;
import org.example.frameworkstudy.dto.BoardReadDTO;
import org.example.frameworkstudy.dto.BoardUpdateDTO;
import org.example.frameworkstudy.entity.Boards;
import org.example.frameworkstudy.exception.InvalidBoardInputException;
import org.example.frameworkstudy.repository.BoardRepository;
import org.example.frameworkstudy.service.impl.BoardServiceImpl;
import org.example.frameworkstudy.service.impl.ViewCountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    public static final String TITLE = "제목";
    public static final String CONTENTS = "내용";
    public static final String AUTHOR = "작성자";
    public static final String EMPTY_STRING = "";
    
    @Mock
    private BoardRepository boardRepository;

    @Mock
    private ViewCountServiceImpl viewCountServiceImpl;

    @InjectMocks
    private BoardServiceImpl boardServiceImpl;

    @Test
    @DisplayName("제목과 내용, 작성자를 입력했을때 게시글 아이디값을 반환")
    void writeBoardTest(int boardId) {
        // given
        // 입력 DTO 생성
        BoardCreateDTO boardCreateDTO = BoardCreateDTO.builder()
                .title(TITLE)
                .contents(CONTENTS)
                .author(AUTHOR)
                .build();

        // 저장될 Entity 생성
        Boards savedBoards = Boards.builder()
                .boardId(boardId) // 저장 후 반환될 ID 값 설정
                .title(TITLE)
                .contents(CONTENTS)
                .author(AUTHOR)
                .build();

        // Mock 설정: boardRepository.save() 호출 시 반환할 값
        given(boardRepository.save(any(Boards.class))).willReturn(savedBoards);

        // when
        BoardCreateDTO result = boardServiceImpl.writeBoard(boardCreateDTO);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getBoardId()).isNotNull();
    }

    @Test
    @DisplayName("게시글 작성시 제목이 없다면 예외를 반환")
    void writeBoard_ThrowsException_WhenTitleIsEmpty(){
        //given
        BoardCreateDTO inputBoardCreateDTO = BoardCreateDTO.builder()
                .title(EMPTY_STRING)
                .contents(CONTENTS)
                .author(AUTHOR)
                .build();

        //when & then
        assertThatThrownBy(() -> boardServiceImpl.writeBoard(inputBoardCreateDTO))
                .isInstanceOf(InvalidBoardInputException.class)
                .hasMessageContaining("게시글 제목이 비어있습니다.");
    }

    @Test
    @DisplayName("게시글 작성시 내용이 없다면 예외를 반환")
    void writeBoard_ThrowsException_WhenContentsIsEmpty(){
        //given
        BoardCreateDTO inputBoardCreateDTO = BoardCreateDTO.builder()
                .title(TITLE)
                .contents(EMPTY_STRING)
                .author(AUTHOR)
                .build();

        //when & then
        assertThatThrownBy(() -> boardServiceImpl.writeBoard(inputBoardCreateDTO))
                .isInstanceOf(InvalidBoardInputException.class)
                .hasMessageContaining("게시글 내용이 비어있습니다.");
    }

    @Test
    @DisplayName("게시글 작성시 작성자가 없다면 예외를 반환")
    void writeBoard_ThrowsException_WhenAuthorIsEmpty(){
        //given
        BoardCreateDTO inputBoardCreateDTO = BoardCreateDTO.builder()
                .title(TITLE)
                .contents(CONTENTS)
                .author(EMPTY_STRING)
                .build();

        //when & then
        assertThatThrownBy(() -> boardServiceImpl.writeBoard(inputBoardCreateDTO))
                .isInstanceOf(InvalidBoardInputException.class)
                .hasMessageContaining("게시글 작성자가 비어있습니다.");
    }

    @Test
    @DisplayName("게시글 목록을 조회하면 BoardReadDTO 리스트를 반환한다.")
    void listBoardTest() {
        //given
        Boards boardListEntity1 = Boards.builder()
                .boardId(1)
                .title("제목1")
                .author("작성자1")
                .build();

        Boards boardListEntity2 = Boards.builder()
                .boardId(2)
                .title("제목2")
                .author("작성자2")
                .build();

        List<Boards> mockBoardList = Arrays.asList(boardListEntity1, boardListEntity2);
        given(boardRepository.findAll()).willReturn(mockBoardList);

        //when
        List<BoardReadDTO> boardList = boardServiceImpl.listBoard();

        //then
        assertThat(boardList.size()).isEqualTo(2);
        assertThat(boardList.get(0).getBoardId()).isEqualTo(1);
        assertThat(boardList.get(1).getBoardId()).isEqualTo(2);
    }

    @Test
    @DisplayName("게시글 번호를 입력받으면 게시글 상세정보를 반환한다.")
    void viewBoardTest() {
        //given
        int boardId = 1;

        Boards boardDetail = Boards.builder()
                .boardId(1)
                .author("상세 작성자")
                .title("상세 제목")
                .contents("상세 내용")
                .viewCount(10)
                .build();

        given(boardRepository.findById(any(Integer.class))).willReturn(Optional.ofNullable(boardDetail));
        doNothing().when(viewCountServiceImpl).incrementViewCount(boardId);
        //when
        BoardReadDTO view = boardServiceImpl.viewBoard(boardId);
        //then
        assertThat(view).isNotNull();
        assertThat(view.getBoardId()).isEqualTo(boardId);
    }

    @Test
    @DisplayName("게시글 ID로 찾지 못하였을때 예외를 반환")
    void shouldThrowException_WhenBoardNotFoundById(){
        //given

        //when

        //then
    }

    @Test
    @DisplayName("게시글 번호와 제목, 내용을 입력받으면 BoardUpdateDTO를 반환한다.")
    void updateBoard() {
        //given
        int boardId = 1;
        BoardUpdateDTO updateDTO = BoardUpdateDTO.builder()
                .boardId(boardId)
                .title("수정 제목")
                .contents("수정 내용")
                .build();
        
        Boards boards = Boards.builder()
                .boardId(boardId)
                .title("기존 제목")
                .contents("기존 내용")
                .build();
        
        given(boardRepository.findById(any(Integer.class))).willReturn(Optional.ofNullable(boards));
        //when
        BoardUpdateDTO result = boardServiceImpl.updateBoard(boardId, updateDTO);

        //then
        verify(boardRepository).findById(boardId);

        assertThat(result).isNotNull();
        assertThat(result.getBoardId()).isEqualTo(boardId);
        assertThat(result.getTitle()).isEqualTo("수정 제목");
        assertThat(result.getContents()).isEqualTo("수정 내용");


    }

    @Test
    @DisplayName("게시글 번호를 입력받으면 게시글을 삭제하고 BoardDeleteDTO반환")
    void deleteBoard() {
        //given
        int boardId = 1;
        BoardDeleteDTO boardDeleteDTO = BoardDeleteDTO.builder()
                .boardId(boardId)
                .build();

        Boards boards = Boards.builder()
                .boardId(boardId)
                .build();

        given(boardRepository.findById(any(Integer.class))).willReturn(Optional.ofNullable(boards));
        //when
        BoardDeleteDTO delete = boardServiceImpl.deleteBoard(boardId);
        //then

        verify(boardRepository).findById(boardId);
        assertThat(boardDeleteDTO.getBoardId()).isEqualTo(delete.getBoardId());

    }

}
