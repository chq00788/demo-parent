package com.chq.demo.security.jwt.common.enums;

/**
 * @author CHQ
 * @Description 错误枚举类
 * @date 2019/5/9
 */
public enum ResultEnum {

    /**
     * 权限不足
     */
    ACCESS_PERMISSION_DENIED(5001, "权限不足"),

    /**
     * token无效，请重新登录
     */
    TOKEN_IS_NOT_VALID(5002, "Token无效，请重新登录"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
