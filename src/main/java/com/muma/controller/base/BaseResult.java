package com.muma.controller.base;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 
 * @author xuyb
 *
 * ajax 请求的返回类型封装JSON结果
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResult<T> implements Serializable {


	private static final long serialVersionUID = -4185151304730685014L;

	private boolean success;

    private T data;

    private String message;

    private String code;

    public BaseResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public BaseResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }
    public BaseResult(boolean success,String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public BaseResult(boolean success,String code, T data) {
        this.success = success;
        this.code = code;
        this.data = data;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
	public String toString() {
		return "BaseResult [success=" + success + ", data=" + data + ", message=" + message + "]";
	}

}
