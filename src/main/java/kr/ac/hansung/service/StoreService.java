package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.StoreDao;
import kr.ac.hansung.model.Product;
import kr.ac.hansung.model.Store;

@Service
public class StoreService {

	@Autowired
	private StoreDao storeDao;
	
	public List<Store> getCurrent() {
		return storeDao.getStores();
	}
	
	public Store getStore(String store_name) {
		return storeDao.getStore(store_name);
	}
	
	public boolean insertStore(Store store) {
		return storeDao.insert(store);
	}
	
	public boolean updateStore(Store store) {
		return storeDao.update(store);
	}
	
	public boolean deleteStore(String store_name) {
		return storeDao.delete(store_name);
	}
}
