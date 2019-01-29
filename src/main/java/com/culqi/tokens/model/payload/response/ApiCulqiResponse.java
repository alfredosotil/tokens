package com.culqi.tokens.model.payload.response;

public class ApiCulqiResponse {

	private Object data;
	private Boolean success;

	public ApiCulqiResponse() {
	}

	public ApiCulqiResponse(Object data, Boolean success) {
		this.data = data;
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
