package com.cencosud.ecom.service;

import org.springframework.stereotype.Service;

import com.cencosud.plattform.exception.ClientException;
import com.cencosud.plattform.exception.StoreClientException;
import com.cencosud.plattform.exception.StoreException;
import com.cencosud.plattform.request.RequestModel;
import com.cencosud.plattform.response.BaseResponse;

@Service
public interface ApiService {

	BaseResponse add(RequestModel request) throws StoreException, ClientException;
	BaseResponse discount(RequestModel request) throws StoreException, ClientException, StoreClientException;
	BaseResponse getAmount(String mail, String storeName) throws StoreException, ClientException, StoreClientException;
}
