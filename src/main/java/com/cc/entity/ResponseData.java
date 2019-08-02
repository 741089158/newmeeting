package com.cc.entity;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResponseData {

    private int count;
    private Integer code;
    private String message;
    private Object data;

    public ResponseData(int count, Integer code, String message, Object data) {
        this.count = count;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseData() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
