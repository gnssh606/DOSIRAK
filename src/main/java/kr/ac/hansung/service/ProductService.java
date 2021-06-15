package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.ProductDao;
import kr.ac.hansung.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	// 모든 제품을 검색
	public List<Product> getCurrent() {
		return productDao.getProducts();
	}
	
	// 바코드와 일치하는 제품을 검색
	public Product getProductwithBarcode(String barcode) {
		return productDao.getProductwithBarcode(barcode);
	}
	
	// 바코드로 시작하는 제품을 검색
	public List<Product> searchProducts(String barcode) {
		return productDao.searchProducts(barcode);
	}

	// 제품명에 검색어가 들어간 제품을 검색
	public List<Product> searchProductsName(String product_name) {
		return productDao.searchProductsName(product_name);
	}
	
	// 새 제품 추가
	public boolean insertProduct(Product product) {
		return productDao.insert(product);
	}
	
	// 제품 정보 수정
	public boolean updateProduct(Product product) {
		return productDao.update(product);
	}
	
	// 제품 정보 삭제
	public boolean deleteProduct(String barcode) {
		return productDao.delete(barcode);
	}
}
