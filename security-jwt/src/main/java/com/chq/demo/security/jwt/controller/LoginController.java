package com.chq.demo.security.jwt.controller;

import com.chq.demo.security.jwt.common.utils.ResultUtil;
import com.chq.demo.security.jwt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/9
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/logout")
    public ResultUtil logout(HttpServletRequest request, HttpServletResponse response) {
        return loginService.logout(request, response);
    }

}
