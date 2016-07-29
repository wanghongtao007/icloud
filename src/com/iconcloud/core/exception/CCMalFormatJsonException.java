package com.iconcloud.core.exception;

public class CCMalFormatJsonException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Object data;
	
	public CCMalFormatJsonException(Object data){
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
