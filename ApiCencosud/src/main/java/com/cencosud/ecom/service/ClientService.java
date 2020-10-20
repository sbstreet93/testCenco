package com.cencosud.ecom.service;

import org.springframework.stereotype.Service;

import com.cencosud.ecom.model.Client;
import com.cencosud.plattform.exception.ClientException;

@Service
public interface ClientService {
	Client getClientByMail(String mail) throws ClientException;
}
