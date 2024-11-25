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
public class BoardUpdateDTO {

    private Integer boardId;
    private String title;
    private String contents;
    private String author;
    private LocalDateTime modifiedAt;

}
