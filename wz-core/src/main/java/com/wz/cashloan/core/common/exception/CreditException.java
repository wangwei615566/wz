package com.wz.cashloan.core.common.exception;


public class CreditException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 响应状态
	 */
	private String code;

	public CreditException() {
		super();
	}
	
	
	public CreditException(String message) {
		super(message);
	}
	
	/**
	 * 构造函数
	 * @param code
	 * @param message
	 * @param
	 */
	public CreditException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	/**
	 * 获取 响应状态
	 * @return 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置 响应状态
	 * @param 
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
}
