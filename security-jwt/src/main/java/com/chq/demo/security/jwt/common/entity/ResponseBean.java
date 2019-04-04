package com.chq.demo.security.jwt.common.entity;

/**
 * @author CHQ
 * @Description
 * @date 2019/4/4
 */
public class ResponseBean {
    private int status;

    private String msg;

    private Object data;

    public ResponseBean(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
