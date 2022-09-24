package com.market.utils;

public class GenericResponse {

	private Boolean status;
	
	private String message;
	
	private Object data;
	
	private Integer code;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public GenericResponse(Boolean status, String message, Object data, Integer code) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.code = code;
	}
	
	
	
	
}
