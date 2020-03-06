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
public class BaseResult implements Serializable {


	private static final long serialVersionUID = -4185151304730685014L;

	private boolean success;

    private Object data;

    private String message;

    private String code;

    public BaseResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public BaseResult(boolean success, final Object data) {
        this.success = success;
        this.data = data;
    }
    public BaseResult(boolean success,String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public BaseResult(boolean success,String code,final Object data) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
