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

@Repository
public class StockDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int getRowCount() {
		String sqlStatement = "select count(*) from stock";
		return jdbcTemplate.queryForObject(sqlStatement, Integer.class);
	}
	
	// 모든 재고 정보를 리턴
	public List<Stock> getStocks() {
		String sqlStatement = "select * from stock";
		return jdbcTemplate.query(sqlStatement, new RowMapper<Stock>() {

			@Override
			public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
				Stock stock = new Stock();
				stock.setId(rs.getInt("id"));
				stock.setBarcode(rs.getString("barcode"));
				stock.setCount(rs.getInt("count"));
				stock.setStore_name(rs.getString("store_name"));
				
				return stock;
			}
			
		});
	}
	
	// 재고 검색
	// 매장 이름과 바코드를 입력하면 재고를 리턴
	public Stock getStock(String store, String barcode) {
		String sqlStatement = "select * from stock where store_name = ? and barcode = ?";
		try {
			Stock stock = jdbcTemplate.queryForObject(sqlStatement, new Object[] {store, barcode}, new RowMapper<Stock>() {

				@Override
				public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
					Stock stock = new Stock();
					stock.setId(rs.getInt("id"));
					stock.setBarcode(rs.getString("barcode"));
					stock.setCount(rs.getInt("count"));
					stock.setStore_name(rs.getString("store_name"));
					
					return stock;
				}		
			});
			
			return stock;
		} catch (EmptyResultDataAccessException e) {
			Stock stock = null;
			
			return stock;
		}
	}
	
	// 판매 처리
	// 매장 이름과 바코드를 입력하면 재고의 count를 1 줄이고 재고를 리턴
	public Stock sellStock(String store, String barcode) {
		String sqlStatement = "select * from stock where store_name = ? and barcode = ?";
		try {
			Stock stock = jdbcTemplate.queryForObject(sqlStatement, new Object[] {store, barcode}, new RowMapper<Stock>() {

				@Override
				public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
					Stock stock = new Stock();
					stock.setId(rs.getInt("id"));
					stock.setBarcode(rs.getString("barcode"));
					stock.setCount(rs.getInt("count") - 1);
					stock.setStore_name(rs.getString("store_name"));
					
					return stock;
				}
				
			});
			
			sqlStatement = "update stock set count=? where id=?";
			
			jdbcTemplate.update(sqlStatement, new Object[] {stock.getCount(), stock.getId()});
			
			return stock;
		} catch (EmptyResultDataAccessException e) {
			Stock stock = null;
			return stock;
		}
	}
	
	// 재고 검색 - 바코드
	// 매장 이름과 바코드를 입력하면 바코드로 시작하는 재고를 리턴
	public List<Stock> searchStocks(String store, String barcode) {
		String sqlStatement = "select * from stock where store_name = ? and barcode LIKE ?";
		return jdbcTemplate.query(sqlStatement, new Object[] {store, barcode + "%"}, new RowMapper<Stock>() {

			@Override
			public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
				Stock stock = new Stock();
				stock.setId(rs.getInt("id"));
				stock.setBarcode(rs.getString("barcode"));
				stock.setCount(rs.getInt("count"));
				stock.setStore_name(rs.getString("store_name"));
				
				return stock;
			}
			
		});
	}
	
	// 재고 검색 - 제품명
	// 바코드를 입력하면 해당 바코드를 가진 재고를 리턴
	public List<Stock> searchStocksWithBarcode(String barcode) {
		String sqlStatement = "select * from stock where barcode = ?";
		return jdbcTemplate.query(sqlStatement, new Object[] {barcode}, new RowMapper<Stock>() {

			@Override
			public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
				Stock stock = new Stock();
				stock.setId(rs.getInt("id"));
				stock.setBarcode(rs.getString("barcode"));
				stock.setCount(rs.getInt("count"));
				stock.setStore_name(rs.getString("store_name"));
				
				return stock;
			}
			
		});
	}
	
	// 재고 수정 - 기존 재고 정보가 없을 경우
	public boolean insert(Stock stock) {
		String barcode = stock.getBarcode();
		int count = stock.getCount();
		String store_name = stock.getStore_name();
		
		String sqlStatement = "insert into stock (barcode, count, store_name) values (?,?,?)";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {barcode, count, store_name}) == 1);
	}

	// 재고 수정 - 기존 재고 정보가 있을 경우
	public boolean update(Stock stock) {
		String barcode = stock.getBarcode();
		int count = stock.getCount();
		String store_name = stock.getStore_name();
		
		String sqlStatement = "update stock set count=? where store_name =? and barcode=?";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {count, store_name, barcode}) == 1);
	}
	
	// 재고 정보 삭제
	public boolean delete(String store, String barcode) {		
		String sqlStatement = "delete from stock where store_name = ? and barcode=?";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {store, barcode}) == 1);
	}
	
	// 매장에 소속된 재고들의 매장명을 변경
	public void changeStore(List<Stock> stocks, String store_name) {
		String sqlStatement = "update stock set store_name=? where id=?";
		
		for (Stock stock : stocks) {

			int id = stock.getId();
			
			jdbcTemplate.update(sqlStatement, new Object[] {store_name, id});
		}
	}
	
	// 해당 바코드를 가진 재고들의 바코드를 변경
	public void changeBarcode(List<Stock> stocks, String barcode) {
		String sqlStatement = "update stock set barcode=? where id=?";
		
		for (Stock stock : stocks) {

			int id = stock.getId();
			
			jdbcTemplate.update(sqlStatement, new Object[] {barcode, id});
		}
	}
	
	// 해당 지점의 모든 재고를 삭제
	public void deleteStore(String store_name) {
		String sqlStatement = "delete from stock where store_name = ?";
		
		jdbcTemplate.update(sqlStatement, new Object[] {store_name});
	}
	
	// 해당 바코드를 가진 모든 재고를 삭제
	public void deleteBarcode(String barcode) {
		String sqlStatement = "delete from stock where barcode = ?";
		
		jdbcTemplate.update(sqlStatement, new Object[] {barcode});
	}
}
