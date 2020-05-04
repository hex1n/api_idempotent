package com.idempotent.common;

/**
 * @Author hex1n
 * @Time 2020/5/3 22:22
 */
public class R {

    private Boolean success;
    private Integer code;
    private String msg;
    private Object data;

    private R() {
    }

    public static R ok() {
        R r = new R();
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(ResultCodeEnum.UNKNOWN_ERROR.getCode());
        r.setMsg(ResultCodeEnum.UNKNOWN_ERROR.getMsg());
        r.setSuccess(ResultCodeEnum.UNKNOWN_ERROR.getSuccess());
        return r;
    }

    public static R setResult(ResultCodeEnum result) {
        R r = new R();
        r.setSuccess(result.getSuccess());
        r.setMsg(result.getMsg());
        r.setCode(result.getCode());
        return r;
    }

    public R data(Object data) {
        this.setData(data);
        return this;
    }

    public R msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    private void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    private void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    private void setData(Object data) {
        this.data = data;
    }
}
