package com.stu.valied.utils;

public enum  ResponseStatus {

    PARAM_VALID_EXCEPTION(10001,"参数校验异常."),

    DO_SUCCESS(10000,"操作成功."),

    DO_FAIL(100002,"操作失败."),

    SYS_EXCEPTION(20001,"系统异常");

    private Integer code;

    private String msg;


    private ResponseStatus(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
