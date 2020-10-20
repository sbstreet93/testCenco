package com.cencosud.plattform.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseModel {
	
	private HttpStatus status = HttpStatus.OK;
	private String message = "Success";
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseEntity<ResponseModel> createSuccessResponse(Object data) {
		ResponseModel response = new ResponseModel();
		response.setData(data);
		return new ResponseEntity<ResponseModel>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseModel> createErrorResponse(Exception e, HttpStatus status) {
		ResponseModel response = new ResponseModel();
		response.setMessage(e.getMessage());
		response.setStatus(status);
		return new ResponseEntity<ResponseModel>(response, status);
	}

}
