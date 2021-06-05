package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.StockDao;
import kr.ac.hansung.model.Product;
import kr.ac.hansung.model.Stock;

@Service
public class StockService {

	@Autowired
	private StockDao stockDao;
	
	public List<Stock> getCurrent() {
		return stockDao.getStocks();
	}
	
	public Stock getStock(String store, String barcode) {
		return stockDao.getStock(store, barcode);
	}
	
	public Stock sellStock(String store, String barcode) {
		return stockDao.sellStock(store, barcode);
	}

	public List<Stock> searchStocks(String store, String keyword) {

		return stockDao.searchStocks(store, keyword);
	}
	
	public List<Stock> searchStocksWithBarcode(String barcode) {
		return stockDao.searchStocksWithBarcode(barcode);
	}
	
	public boolean insertStock(Stock stock) {
		return stockDao.insert(stock);
	}
	
	public boolean updateStock(Stock stock) {
		return stockDao.update(stock);
	}
	
	public boolean deleteStock(String store, String barcode) {
		return stockDao.delete(store, barcode);
	}
	
	public void changeStore(List<Stock> stocks, String store_name) {
		stockDao.changeStore(stocks, store_name);
	}
	
	public void changeBarcode(List<Stock> stocks, String barcode) {
		stockDao.changeBarcode(stocks, barcode);
	}

	public void deleteStore(String store_name) {
		stockDao.deleteStore(store_name);
	}
	
	public void deleteProduct(String barcode) {
		stockDao.deleteBarcode(barcode);
	}
}
