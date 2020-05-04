package com.idempotent.common;

/**
 * @Author hex1n
 * @Time 2020/5/3 22:27
 */
public enum ResultCodeEnum {

    SUCCESS(true, 200, "成功"),

    UNKNOWN_ERROR(false, 20001, "未知错误"),

    PARAM_ERROR(false, 20002, "参数错误"),
    ;

    private Boolean success;
    private Integer code;
    private String msg;

    ResultCodeEnum(boolean success, Integer code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
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
