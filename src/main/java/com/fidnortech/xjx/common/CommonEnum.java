package com.fidnortech.xjx.common;

/**
 * 异常码
 */
public enum CommonEnum  {



	PARAM_VALIDATED(50000,"参数校验异常!");

	/** 错误码 */
	private int code;

	/** 错误描述 */
	private String message;

	CommonEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
