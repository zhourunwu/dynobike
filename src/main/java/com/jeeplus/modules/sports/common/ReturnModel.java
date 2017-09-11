package com.jeeplus.modules.sports.common;

/**
 * Created by DELL on 2017/7/27.
 */
public class ReturnModel<E> {

    //接口调用状态：success：200 ，fail：201，error：202
    private int code;
    //接口调用返回说明
    private String message;
    //返回对象Class，即泛型变量E的Class的字符串
    private String jsonType;
    //泛型变量，可为对象，可为列表，可为字符串，数字；
    private E Data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJsonType() {
        return jsonType;
    }

    public void setJsonType(String jsonType) {
        this.jsonType = jsonType;
    }

    public E getData() {
        return Data;
    }

    public void setData(E data) {
        Data = data;
    }
}
