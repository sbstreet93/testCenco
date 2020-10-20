package com.cencosud.ecom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cencosud.ecom.model.Store;

@Component
public class StoreRepositoryImpl implements StoreRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Optional<Store> getStoreByName(String name) {
		String sql = "select id, store_name from store where store_name = ?";
		Object[] args = new Object[] { name };

		List<Object> result = this.jdbcTemplate.query(sql, args, (rs, rowNum) -> {

			Store _store = new Store();

			_store.setId(rs.getLong("id"));
			_store.setStoreName(rs.getString("store_name"));

			return _store;
		});
		return result.isEmpty() ? Optional.empty() : Optional.of((Store) result.get(0));
	}

}
