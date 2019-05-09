package com.chq.demo.security.jwt.common.handle;

import com.chq.demo.security.jwt.common.enums.ResultEnum;
import com.chq.demo.security.jwt.common.utils.ResponseUtil;
import com.chq.demo.security.jwt.common.utils.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CHQ
 * @Description 权限认证异常处理器
 * @date 2019/5/9
 */
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.write(response, ResultUtil.error(ResultEnum.TOKEN_IS_NOT_VALID));
    }
}
