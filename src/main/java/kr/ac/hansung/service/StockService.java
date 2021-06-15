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
	
	// 모든 재고 정보 리턴
	public List<Stock> getCurrent() {
		return stockDao.getStocks();
	}
	
	// store 매장에 존재하는 바코드를 가진 제품을 검색
	public Stock getStock(String store, String barcode) {
		return stockDao.getStock(store, barcode);
	}
	
	// store 매장에 존재하는 바코드를 가진 제품을 판매
	public Stock sellStock(String store, String barcode) {
		return stockDao.sellStock(store, barcode);
	}

	// store 매장에 존재하는 검색어로 시작하는 바코드를 가진 재고들을 검색
	public List<Stock> searchStocks(String store, String barcode) {
		return stockDao.searchStocks(store, barcode);
	}
	
	// 제품의 바코드를 수정했을 때 재고의 바코드도 함께 수정하기 위해 사용
	// 바코드를 가진 재고를 검색
	public List<Stock> searchStocksWithBarcode(String barcode) {
		return stockDao.searchStocksWithBarcode(barcode);
	}
	
	// 새 재고 입력
	public boolean insertStock(Stock stock) {
		return stockDao.insert(stock);
	}
	
	// 재고 수정
	public boolean updateStock(Stock stock) {
		return stockDao.update(stock);
	}
	
	// 재고 삭제
	public boolean deleteStock(String store, String barcode) {
		return stockDao.delete(store, barcode);
	}
	
	// 매장명이 바뀔 때 해당 매장의 재고들도 함께 매장명을 변경
	public void changeStore(List<Stock> stocks, String store_name) {
		stockDao.changeStore(stocks, store_name);
	}
	
	// 제품의 바코드가 바뀔 때 해당 바코드의 재고들도 함께 바코드를 변경
	public void changeBarcode(List<Stock> stocks, String barcode) {
		stockDao.changeBarcode(stocks, barcode);
	}

	// 매장을 삭제할 때 해당 매장이 가진 재고 정보를 전부 삭제
	public void deleteStore(String store_name) {
		stockDao.deleteStore(store_name);
	}
	
	// 제품 정보를 삭제할 때 해당 제품에 관련된 재고 정보를 전부 삭제
	public void deleteProduct(String barcode) {
		stockDao.deleteBarcode(barcode);
	}
}
