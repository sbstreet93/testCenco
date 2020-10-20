package com.cencosud.ecom.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cencosud.ecom.model.StoreClient;

@Repository
public interface StoreClientRepository {
	Optional<StoreClient> getStoreClient(StoreClient storeClient);
	StoreClient save(StoreClient storeClient);
}
