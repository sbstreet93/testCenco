package com.cencosud.ecom.service;

import org.springframework.stereotype.Service;

import com.cencosud.ecom.model.Store;
import com.cencosud.plattform.exception.StoreException;

@Service
public interface StoreService {
	Store getStoreByName(String name) throws StoreException;
}
