package com.cencosud.ecom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cencosud.ecom.model.StoreClient;

@Component
public class StoreClientRepositoryImpl implements StoreClientRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	JpaStoreClientRepository jpaRepository;
	
	@Override
	public Optional<StoreClient> getStoreClient(StoreClient storeClient) {
		String sql = "select id, id_store, id_client, amount from store_client "
				+ "where id_store = ? "
				+ "and id_client = ?";
		Object[] args = new Object[] { storeClient.getStoreId(), storeClient.getClientId() };

		List<Object> result = this.jdbcTemplate.query(sql, args, (rs, rowNum) -> {

			StoreClient _storeClient = new StoreClient();

			_storeClient.setId(rs.getLong("id"));
			_storeClient.setStoreId(rs.getLong("id_store"));
			_storeClient.setClientId(rs.getLong("id_client"));
			_storeClient.setAmount(rs.getInt("amount"));

			return _storeClient;
		});
		return result.isEmpty() ? Optional.empty() : Optional.of((StoreClient) result.get(0));
	}

	@Override
	public StoreClient save(StoreClient storeClient) {
		return jpaRepository.saveAndFlush(storeClient);
	}

}
