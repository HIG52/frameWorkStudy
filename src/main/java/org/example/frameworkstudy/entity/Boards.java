package org.example.frameworkstudy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.frameworkstudy.cmmnEntity.Timestamped;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Boards extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARDID", nullable = false)
    private Integer boardId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Lob
    @Column(name = "CONTENTS", nullable = false)
    private String contents;

    @Column(name = "AUTHOR", nullable = false, length = 100)
    private String author;

    // 값 변경 메서드
    public void changeContent(String contents) {
        if (contents != null) {
            this.contents = contents;
        }
    }

    public void changeTitle(String title) {
        if (title != null) {
            this.title = title;
        }
    }
}
