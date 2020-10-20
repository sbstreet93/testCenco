package com.cencosud.ecom.service;

import org.springframework.stereotype.Service;

import com.cencosud.ecom.model.StoreClient;
import com.cencosud.plattform.exception.StoreClientException;

@Service
public interface StoreClientService {
	StoreClient add(StoreClient storeClient);
	StoreClient discount(StoreClient storeClient) throws StoreClientException;
	StoreClient getAmount(StoreClient storeClient) throws StoreClientException;
}
