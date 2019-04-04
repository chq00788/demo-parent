package com.chq.demo.security.jwt.system.service;

import com.chq.demo.security.jwt.system.model.UserModel;
import org.springframework.stereotype.Service;

/**
 * @author CHQ
 * @Description
 * @date 2019/4/4
 */
@Service
public class UserService {

    public UserModel getUser(String username) {


        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword("123456");
        user.setRole("ROLE_ADMIN");
        return user;
    }
}
