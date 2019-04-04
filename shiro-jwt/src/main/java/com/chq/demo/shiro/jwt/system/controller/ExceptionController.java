package com.chq.demo.shiro.jwt.system.controller;

import com.chq.demo.shiro.jwt.common.entity.Response;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CHQ
 * @Description
 * @date 2019/4/2
 */
@RestControllerAdvice
public class ExceptionController {


    /**
     * 捕捉shiro的异常
     *
     * @return
     */
    @ExceptionHandler(ShiroException.class)
    public Response<String> handle401() {
        Response<String> result = new Response<>();
        result.setError("您没有权限访问!");
        return result;
    }

    /**
     * 捕捉其他所有异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response<String> globalException(HttpServletRequest request, Throwable ex) {
        Response<String> result = new Response<>();
        result.setError("访问异常");
        return result;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
