package org.example.frameworkstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    private String userId;
    private String password;

    public UserLoginDTO(String userid) {
        this.userId = userid;
    }
}
