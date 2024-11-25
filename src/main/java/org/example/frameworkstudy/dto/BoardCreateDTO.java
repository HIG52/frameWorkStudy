package org.example.frameworkstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateDTO {

    private boolean isSuccess;
    private Integer boardId;
    private String title;
    private String contents;
    private String author;


    public BoardCreateDTO(boolean isSuccess, Integer boardId) {
        this.isSuccess = isSuccess;
        this.boardId = boardId;
    }


}
