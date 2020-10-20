package com.cencosud.ecom.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.ecom.service.ApiService;
import com.cencosud.plattform.exception.ClientException;
import com.cencosud.plattform.exception.StoreClientException;
import com.cencosud.plattform.exception.StoreException;
import com.cencosud.plattform.request.RequestModel;
import com.cencosud.plattform.response.ResponseModel;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	ApiService service;

	@PostMapping
	private ResponseEntity<ResponseModel> add(@RequestBody RequestModel request) {
		ResponseEntity<ResponseModel> response;
		ResponseModel responseModel = new ResponseModel();
		try {
			response = responseModel.createSuccessResponse(service.add(request));
		} catch (StoreException | ClientException e) {
			response = responseModel.createErrorResponse(e, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@PutMapping
	private ResponseEntity<ResponseModel> discount(@RequestBody RequestModel request) {
		ResponseEntity<ResponseModel> response;
		ResponseModel responseModel = new ResponseModel();
		try {
			response = responseModel.createSuccessResponse(service.discount(request));
		} catch (StoreException | ClientException | StoreClientException e) {
			response = responseModel.createErrorResponse(e, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping
	private ResponseEntity<ResponseModel> getAmount(@RequestParam("mail") String mail, @RequestParam("storeName") String storeName) {
		ResponseEntity<ResponseModel> response;
		ResponseModel responseModel = new ResponseModel();
		try {
			response = responseModel.createSuccessResponse(service.getAmount(mail, storeName));
		} catch (StoreException | ClientException | StoreClientException e) {
			response = responseModel.createErrorResponse(e, HttpStatus.BAD_REQUEST);
		} 
		return response;
	}
}
