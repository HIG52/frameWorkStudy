package org.example.frameworkstudy.service;

import org.example.frameworkstudy.dto.UserJoinDTO;
import org.example.frameworkstudy.dto.UserLoginDTO;

public interface UserService {

    UserJoinDTO userJoin(UserJoinDTO userJoinDTO);

    String userLogin(UserLoginDTO userLoginDTO);
}
