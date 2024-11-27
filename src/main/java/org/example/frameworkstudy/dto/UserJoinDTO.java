package org.example.frameworkstudy.dto;

import lombok.*;

import java.util.Objects;

//실제 엔티티 객체를 통해 데이터를 전달하지 않고 DTO를 통해 데이터를 주고받기 위해 생성
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinDTO {

    private String userId;
    private String name;
    private String password;

    //나중에 정적 팩토리 메서드로 변경해보기
    public UserJoinDTO(String userid) {
        this.userId = userid;
    }

}
