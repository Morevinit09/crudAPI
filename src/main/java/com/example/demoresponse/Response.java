package com.example.demoresponse;

public class Response {
	private Object Data;

	private Integer totalRecords;

	private String message;

	private String error;

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public Object getData() {
		return Data;
	}

	public void setData(Object data) {
		Data = data;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
