package com.chq.demo.security.jwt.system.model;

/**
 * @author CHQ
 * @Description
 * @date 2019/4/4
 */
public class UserModel {

    private String username;

    private String password;

    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
