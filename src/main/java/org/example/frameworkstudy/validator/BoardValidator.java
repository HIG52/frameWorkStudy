package org.example.frameworkstudy.validator;

import org.example.frameworkstudy.dto.BoardCreateDTO;
import org.example.frameworkstudy.exception.InvalidBoardInputException;

public class BoardValidator {

    public static void checkBoardCreatDTO(BoardCreateDTO boardCreateDTO) {
        if(boardCreateDTO.getTitle().isEmpty()) {
            throw new InvalidBoardInputException("게시글 제목이 비어있습니다.");
        }

        if(boardCreateDTO.getContents().isEmpty()) {
            throw new InvalidBoardInputException("게시글 내용이 비어있습니다.");
        }

        if(boardCreateDTO.getAuthor().isEmpty()) {
            throw new InvalidBoardInputException("게시글 작성자가 비어있습니다.");
        }
    }

}
