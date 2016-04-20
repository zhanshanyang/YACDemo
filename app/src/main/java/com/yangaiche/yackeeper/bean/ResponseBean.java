package com.yangaiche.yackeeper.bean;

/**
 * Created by ui on 16/4/19.
 */
public class ResponseBean<T> {

    public static String CODE_KEY = "code";
    public static String MESSAGE_KEY = "message";

    public String code;
    public String message;
    public T data;

    public ResponseBean(){}

    public ResponseBean(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess(){
        return "00000".equals(code);
    }

    /*
     {"code": "00000", "message": "success"}
     {"code": "00000", "message": "success", "data":{...}}
     {"code": "00000", "message": "success", "data":[{ }]
     */
}
