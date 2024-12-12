package org.example.frameworkstudy.service;


import jakarta.transaction.Transactional;
import org.example.frameworkstudy.dto.BoardCreateDTO;
import org.example.frameworkstudy.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardServiceTotalTest {

    public static final String TITLE = "제목";
    public static final String CONTENTS = "내용";
    public static final String AUTHOR = "작성자";

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void setUpCleanDatabase() {
        boardRepository.deleteAll();
        boardRepository.flush();
    }

    @Test
    @DisplayName("제목과 내용, 작성자를 입력했을때 게시글 아이디값을 반환")
    void writeBoardTest(){

        // given
        BoardCreateDTO boardCreateDTO = BoardCreateDTO.builder()
                .title(TITLE)
                .contents(CONTENTS)
                .author(AUTHOR)
                .build();

        // when
        BoardCreateDTO result = boardService.writeBoard(boardCreateDTO);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getBoardId()).isNotNull();
    }



}
