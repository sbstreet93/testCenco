package com.cencosud.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cencosud.ecom.model.Client;
import com.cencosud.ecom.model.Store;
import com.cencosud.ecom.model.StoreClient;
import com.cencosud.plattform.exception.ClientException;
import com.cencosud.plattform.exception.StoreClientException;
import com.cencosud.plattform.exception.StoreException;
import com.cencosud.plattform.request.RequestModel;
import com.cencosud.plattform.response.BaseResponse;

@Component
public class ApiServiceImpl implements ApiService {

	@Autowired
	private StoreService storeService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private StoreClientService storeClientService;

	@Override
	public BaseResponse add(RequestModel request) throws StoreException, ClientException {
		BaseResponse response = new BaseResponse();
		StoreClient storeClient = getStoreClient(request);
		storeClient = storeClientService.add(storeClient);
		
		response.setAmount(storeClient.getAmount());
		response.setMail(request.getMail());
		response.setStoreName(request.getStoreName());
		
		return response;
	}

	@Override
	public BaseResponse discount(RequestModel request)throws StoreException, ClientException, StoreClientException {
		BaseResponse response = new BaseResponse();
		StoreClient storeClient = getStoreClient(request);
		storeClient = storeClientService.discount(storeClient);
		
		response.setAmount(storeClient.getAmount());
		response.setMail(request.getMail());
		response.setStoreName(request.getStoreName());
		
		return response;

	}

	@Override
	public BaseResponse getAmount(String mail, String storeName) throws StoreException, ClientException, StoreClientException {
		RequestModel request = new RequestModel();
		BaseResponse response = new BaseResponse();
		
		request.setMail(mail);
		request.setStoreName(storeName);
		
		StoreClient storeClient = getStoreClient(request);
		storeClient = storeClientService.getAmount(storeClient);
		
		response.setAmount(storeClient.getAmount());
		response.setMail(request.getMail());
		response.setStoreName(request.getStoreName());
		
		return response;
	}

	private StoreClient getStoreClient(RequestModel request) throws StoreException, ClientException {
		Store store = storeService.getStoreByName(request.getStoreName());
		Client client = clientService.getClientByMail(request.getMail());
		StoreClient storeClient = new StoreClient();
		storeClient.setStoreId(store.getId());
		storeClient.setClientId(client.getId());
		storeClient.setAmount(request.getAmount());
		return storeClient;
	}
}
