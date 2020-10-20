package com.cencosud.ecom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cencosud.ecom.model.StoreClient;
import com.cencosud.ecom.repository.StoreClientRepository;
import com.cencosud.plattform.exception.StoreClientException;

@Component
public class StoreClientServiceImpl implements StoreClientService {

	@Autowired
	StoreClientRepository repository;

	@Override
	public StoreClient add(StoreClient storeClient) {
		Optional<StoreClient> _storeClient = repository.getStoreClient(storeClient);
		if (_storeClient.isPresent()) {
			storeClient.setId(_storeClient.get().getId());
			storeClient.setAmount(storeClient.getAmount() + _storeClient.get().getAmount());
		}
		return repository.save(storeClient);

	}

	@Override
	public StoreClient discount(StoreClient storeClient) throws StoreClientException {
		int amountDiscount = storeClient.getAmount();
		storeClient = getAmount(storeClient);
		amountDiscount = storeClient.getAmount() - amountDiscount;
		if (amountDiscount < 0)
			throw new StoreClientException(
					"The amount to discount exceded the maximum (" + storeClient.getAmount() + ")");
		storeClient.setAmount(amountDiscount);
		return repository.save(storeClient);
	}

	@Override
	public StoreClient getAmount(StoreClient storeClient) throws StoreClientException {
		Optional<StoreClient> _storeClient = repository.getStoreClient(storeClient);
		if (!_storeClient.isPresent()) {
			throw new StoreClientException("The relationship between client and store provided doesn't exist");
		}
		return _storeClient.get();
	}

}
