package com.cencosud.ecom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cencosud.ecom.model.Client;
import com.cencosud.ecom.repository.ClientRepository;
import com.cencosud.plattform.exception.ClientException;

@Component
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository repository;

	@Override
	public Client getClientByMail(String mail) throws ClientException {
		Optional<Client> client = repository.getClientByMail(mail);
		if(!client.isPresent())
			throw new ClientException("Client doesn't exist");
		return client.get();
	}

}
