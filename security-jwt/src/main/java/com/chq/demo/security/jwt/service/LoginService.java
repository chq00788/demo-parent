package com.chq.demo.security.jwt.service;

import com.chq.demo.security.jwt.common.utils.ResultUtil;
import com.chq.demo.security.jwt.model.PermissionMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/9
 */
@Service
public class LoginService {

    public ResultUtil logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            PermissionMap.map = null;
            PermissionMap.list = null;
        }
        return ResultUtil.success();
    }
}
