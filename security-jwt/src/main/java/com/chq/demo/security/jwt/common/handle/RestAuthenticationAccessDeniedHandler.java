package com.chq.demo.security.jwt.common.handle;

import com.chq.demo.security.jwt.common.enums.ResultEnum;
import com.chq.demo.security.jwt.common.utils.ResponseUtil;
import com.chq.demo.security.jwt.common.utils.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CHQ
 * @Description 权限不足处理
 * @date 2019/5/9
 */
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.write(response, ResultUtil.error(ResultEnum.ACCESS_PERMISSION_DENIED));
    }
}
