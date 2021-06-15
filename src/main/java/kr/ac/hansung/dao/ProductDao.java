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

import kr.ac.hansung.model.Product;
import kr.ac.hansung.model.Stock;

@Repository
public class ProductDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int getRowCount() {
		String sqlStatement = "select count(*) from product";
		return jdbcTemplate.queryForObject(sqlStatement, Integer.class);
	}
	
	// 제품 검색 - 제품명
	// 제품명으로 제품을 검색하여 리턴
	public Product getProduct(String name) {
		String sqlStatement = "select * from product where name = ?";
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] {name}, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setBarcode(rs.getString("barcode"));
				product.setPrice(rs.getInt("price"));
				product.setCategory(rs.getString("category"));
				
				return product;
			}
			
		});
	}
	
	// 제품 검색 - 바코드
	// 바코드로 제품을 검색하여 리턴
	public Product getProductwithBarcode(String barcode) {
		String sqlStatement = "select * from product where barcode = ?";
		try {
			Product product = jdbcTemplate.queryForObject(sqlStatement, new Object[] {barcode}, new RowMapper<Product>() {

				@Override
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setProduct_name(rs.getString("product_name"));
					product.setBarcode(rs.getString("barcode"));
					product.setPrice(rs.getInt("price"));
					product.setCategory(rs.getString("category"));
					
					return product;
				}
				
			});
			
			return product;
		} catch (EmptyResultDataAccessException e) {
			Product product = null;
			return product;
		}
	}
	
	// 모든 제품을 리턴
	public List<Product> getProducts() {
		String sqlStatement = "select * from product";
		return jdbcTemplate.query(sqlStatement, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setBarcode(rs.getString("barcode"));
				product.setPrice(rs.getInt("price"));
				product.setCategory(rs.getString("category"));
				
				return product;
			}
			
		});
	}
	
	// 제품 검색 - 바코드
	// 바코드를 입력하면 바코드로 시작하는 제품을 리턴
	public List<Product> searchProducts(String barcode) {
		String sqlStatement = "select * from product where barcode LIKE ?";
		return jdbcTemplate.query(sqlStatement, new Object[] {barcode + "%"}, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setBarcode(rs.getString("barcode"));
				product.setPrice(rs.getInt("price"));
				product.setCategory(rs.getString("category"));
				
				return product;
			}
			
		});
	}
	
	// 제품 검색 - 제품명
	// 상품 이름을 입력하면 해당 문자열이 포함된 제품을 리턴
	public List<Product> searchProductsName(String product_name) {
		String sqlStatement = "select * from product where product_name LIKE ?";
		return jdbcTemplate.query(sqlStatement, new Object[] {"%" + product_name + "%"}, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setBarcode(rs.getString("barcode"));
				product.setPrice(rs.getInt("price"));
				product.setCategory(rs.getString("category"));
				
				return product;
			}
			
		});
	}
	
	// 새 제품 등록
	public boolean insert(Product product) {
		String product_name = product.getProduct_name();
		String barcode = product.getBarcode();
		int price = product.getPrice();
		String category = product.getCategory();
		
		String sqlStatement = "insert into product (product_name, barcode, price, category) values (?, ?,?,?)";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {product_name, barcode, price, category}) == 1);
	}

	// 제품 정보 수정
	public boolean update(Product product) {
		String product_name = product.getProduct_name();
		String barcode = product.getBarcode();
		int price = product.getPrice();
		String category = product.getCategory();
		
		String sqlStatement = "update product set product_name=?, price=?, category=? where barcode=?";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {product_name, price, category, barcode}) == 1);
	}
	
	// 제품 정보 삭제
	public boolean delete(String barcode) {		
		String sqlStatement = "delete from product where barcode=?";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {barcode}) == 1);
	}

}
