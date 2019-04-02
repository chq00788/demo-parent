package com.chq.demo.shiro.jwt.controller;

import com.chq.demo.shiro.jwt.common.entity.Response;
import com.chq.demo.shiro.jwt.common.utils.JwtUtil;
import com.chq.demo.shiro.jwt.model.UserModel;
import com.chq.demo.shiro.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author CHQ
 * @Description
 * @date 2019/4/2
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param user
     * @param response
     * @return
     */
    @PostMapping("/login")
    public Response<String> login(@RequestBody UserModel user, HttpServletResponse response) {
        Response<String> result = new Response<>();
        String realPassword = userService.getPassword(user.getUsername());
        if (realPassword == null) {
            result.setError("用户名错误");
            return result;
        } else if (!realPassword.equals(user.getPassword())) {
            result.setError("密码错误");
            return result;
        } else {
            String token = JwtUtil.createToken(user.getUsername());
            response.setHeader("auth-token", token);
            result.setResult("登录成功");
            return result;
        }
    }

    @RequestMapping(path = "/unauthorized/{message}")
    public Response<String> unauthorized(@PathVariable String message) {
        Response<String> result = new Response<>();
        result.setError(message);
        return result;
    }
}
