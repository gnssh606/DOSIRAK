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
	
	public List<Product> getCurrent() {
		return productDao.getProducts();
	}
	
	public Product getProductwithBarcode(String barcode) {
		return productDao.getProductwithBarcode(barcode);
	}
	
	public List<Product> searchProducts(String barcode) {
		return productDao.searchProducts(barcode);
	}

	public List<Product> searchProductsName(String product_name) {
		return productDao.searchProductsName(product_name);
	}
	
	public boolean insertProduct(Product product) {
		return productDao.insert(product);
	}
	
	public boolean updateProduct(Product product) {
		return productDao.update(product);
	}
	
	public boolean deleteProduct(String barcode) {
		return productDao.delete(barcode);
	}
}
