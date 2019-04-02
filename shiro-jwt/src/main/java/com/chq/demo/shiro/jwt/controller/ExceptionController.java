package com.chq.demo.shiro.jwt.controller;

import com.chq.demo.shiro.jwt.common.entity.ResultMap;
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
    public ResultMap handle401() {
        ResultMap resultMap = new ResultMap();
        return resultMap.fail().code(401).message("您没有权限访问！");
    }

    /**
     * 捕捉其他所有异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultMap globalException(HttpServletRequest request, Throwable ex) {
        ResultMap resultMap = new ResultMap();
        return resultMap.fail()
                .code(getStatus(request).value())
                .message("访问出错，无法访问: " + ex.getMessage());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
