package com.chq.demo.security.jwt.model;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/9
 */
public class LoginForm {

    private String username;

    private String password;

    private Boolean remember = false;

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

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }
}
