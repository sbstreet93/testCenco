package com.cencosud.ecom.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cencosud.ecom.model.Store;

@Repository
public interface StoreRepository {
	Optional<Store> getStoreByName(String name);
}
