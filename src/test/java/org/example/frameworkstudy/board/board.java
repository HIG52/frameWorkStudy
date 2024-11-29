package org.example.frameworkstudy.board;

import org.example.frameworkstudy.dto.BoardCreateDTO;
import org.example.frameworkstudy.repository.BoardRepository;
import org.example.frameworkstudy.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class board {

    @Autowired
    private BoardService boardService;

    @MockBean
    private BoardRepository boardRepository;

    @Test
    void writeBoardTest(){

    }

}
