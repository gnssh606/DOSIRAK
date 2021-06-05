package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Stock;
import kr.ac.hansung.model.Store;

@Repository
public class StoreDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int getRowCount() {
		String sqlStatement = "select count(*) from store";
		return jdbcTemplate.queryForObject(sqlStatement, Integer.class);
	}
	
	public Store getStore(String store_name) {
		String sqlStatement = "select * from store where store_name = ?";
		try {
			Store store = jdbcTemplate.queryForObject(sqlStatement, new Object[] {store_name}, new RowMapper<Store>() {

				@Override
				public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
					Store store = new Store();
					store.setId(rs.getInt("id"));
					store.setStore_name(rs.getString("store_name"));
					store.setLocation(rs.getString("location"));
					
					return store;
				}
				
			});

			return store;
		} catch (EmptyResultDataAccessException e) {
			Store store = null;
			
			return store;
		}
	}
	
	public List<Store> getStores() {
		String sqlStatement = "select * from store";
		return jdbcTemplate.query(sqlStatement, new RowMapper<Store>() {

			@Override
			public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
				Store store = new Store();
				store.setId(rs.getInt("id"));
				store.setStore_name(rs.getString("store_name"));
				store.setLocation(rs.getString("location"));

				
				return store;
			}
			
		});
	}
	
	public boolean insert(Store store) {
		String store_name = store.getStore_name();
		String location = store.getLocation();
		
		String sqlStatement = "insert into store (store_name, location) values (?,?)";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {store_name, location}) == 1);
	}

	public boolean update(Store store) {
		String store_name = store.getStore_name();
		String location = store.getLocation();
		
		String sqlStatement = "update store set location=? where store_name=?";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {location, store_name}) == 1);
	}
	
	public boolean delete(String store_name) {		
		String sqlStatement = "delete from store where store_name=?";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {store_name}) == 1);
	}
	
}
