package org.example.frameworkstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardReadDTO {

    private Integer boardId;
    private String title;
    private String content;
    private String author;
    private LocalDateTime modifiedAt;

    public BoardReadDTO(Integer boardId, String title, String author, LocalDateTime modifiedAt) {
        this.boardId = boardId;
        this.title = title;
        this.author = author;
        this.modifiedAt = modifiedAt;
    }
}
