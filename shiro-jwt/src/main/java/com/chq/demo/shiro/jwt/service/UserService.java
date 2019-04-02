package com.chq.demo.shiro.jwt.service;

import org.springframework.stereotype.Service;

/**
 * @author CHQ
 * @Description
 * @date 2019/4/2
 */
@Service
public class UserService {

    public String getPassword(String username){
        return "123456";
    }

    public String getRole(String username){
        return "admin";
    }

    public String getRolePermission(String username){
        return "";
    }

    public String getPermission(String username){
        return "";
    }

    public int checkUserBanStatus(String username){
        return 0;
    }
}
