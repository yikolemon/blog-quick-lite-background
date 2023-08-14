package com.yikolemon.vo;
import java.io.Serializable;

public class AjaxResult implements Serializable {

	// 返回状态码 (默认200:成功,-1:失败)
	private int code = 200;

	// 返回的中文消息
	private String message;

	// 成功时携带的数据
	private Object data;

	private AjaxResult() {
	}

	private AjaxResult(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}


	public static AjaxResult addSuccess(String message) {
		return new AjaxResult(200,message,null);
	}

	public static AjaxResult addSuccess(Object data) {
		return new AjaxResult(200,"",data);
	}

	public static AjaxResult addSuccess(String message,Object data) {
		return new AjaxResult(200,message,data);
	}

	public static AjaxResult addError(String message) {
		return new AjaxResult(-1,message,null);
	}

}
