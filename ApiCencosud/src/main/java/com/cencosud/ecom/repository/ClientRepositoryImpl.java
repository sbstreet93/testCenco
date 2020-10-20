package com.cencosud.ecom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cencosud.ecom.model.Client;

@Component
public class ClientRepositoryImpl implements ClientRepository {

	@Autowired	
	JdbcTemplate jdbcTemplate;

	@Override
	public Optional<Client> getClientByMail(String mail) {
		String sql = "select id, mail from client where mail = ?";
		Object[] args = new Object[] { mail };

		List<Object> result = this.jdbcTemplate.query(sql, args, (rs, rowNum) -> {

			Client _client = new Client();

			_client.setId(rs.getLong("id"));
			_client.setMail(rs.getString("mail"));

			return _client;
		});
		return result.isEmpty() ? Optional.empty() : Optional.of((Client) result.get(0));
	}

}
