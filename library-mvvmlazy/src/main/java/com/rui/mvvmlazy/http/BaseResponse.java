package com.rui.mvvmlazy.http;

/**
 * Created by zjr on 2020/5/10.
 * 该类仅供参考，实际业务返回的固定字段, 根据需求来定义，
 */
public class BaseResponse<T> {
    public int code;
    public String message;
//    public DataContent<T> data;
    public T result;

//    public static class DataContent<T> {
//        public int status;
//        public String msg;
//        public T data;
//    }
}
