package com.chq.demo.shiro.jwt.system.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author CHQ
 * @Description
 * @date 2019/4/2
 */
@Service
public class UserService {

    public String getPassword(String username) {
        return "123456";
    }

    public String getRole(String username) {
        return "admin";
    }

    public String getRolePermission(String username) {
        return "";
    }

    public Set<String> getPermission(String username) {
        Set<String> permissions = new HashSet<>();
        permissions.add("system:user:getMessage");
        return permissions;
    }

    public int checkUserBanStatus(String username) {
        return 0;
    }
}
