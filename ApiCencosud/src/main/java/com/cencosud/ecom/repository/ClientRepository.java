package com.cencosud.ecom.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cencosud.ecom.model.Client;

@Repository
public interface ClientRepository {
	Optional<Client> getClientByMail(String mail);
}
