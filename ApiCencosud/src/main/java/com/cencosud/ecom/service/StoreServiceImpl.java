package com.cencosud.ecom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cencosud.ecom.model.Store;
import com.cencosud.ecom.repository.StoreRepository;
import com.cencosud.plattform.exception.StoreException;

@Component
public class StoreServiceImpl implements StoreService{

	@Autowired
	StoreRepository repository;
	
	@Override
	public Store getStoreByName(String name) throws StoreException {
		Optional<Store> store = repository.getStoreByName(name);
		if(!store.isPresent())
			throw new StoreException("Store doesn't exist");
		return store.get();
	}

}
