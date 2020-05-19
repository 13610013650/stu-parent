package com.stu.valied.utils;

import java.io.Serializable;


/**
 * 页面响应对象封装类
 * @param <T>
 */
public class R<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    public R(){

    }

    public R(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public R(String msg, T data){
        this.msg = msg;
        this.data = data;
    }

    public R(Integer code,String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static  R success(){
        return new R(ResponseStatus.DO_SUCCESS.getCode(),ResponseStatus.DO_SUCCESS.getMsg());
    }

    public static  R success(Object data){
        return new R(ResponseStatus.DO_SUCCESS.getCode(),ResponseStatus.DO_SUCCESS.getMsg(),data);
    }

    public static  R success(Integer code,String msg, Object data){
        return new R(code,msg,data);
    }

    public static  R success(Integer code,String msg){
        return new R(code,msg);
    }

    public static R fail(){
        return new R(ResponseStatus.DO_FAIL.getCode(),ResponseStatus.DO_FAIL.getMsg());
    }

    public static R fail(Integer code,String msg){
        return new R(code,msg);
    }

    public static R fail(Integer code,String msg, Object data){
        return new R(code,msg,data);
    }

    public  R setCode(Integer code){
        this.code = code;
        return this;
    }

    public R setMsg(String msg){
        this.msg = msg;
        return this;
    }

    public R setData(T data){
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
