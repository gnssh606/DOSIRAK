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
	
	// 모든 매장 정보를 반환
	public List<Store> getCurrent() {
		return storeDao.getStores();
	}
	
	// 매장명을 가진 매장을 검색
	public Store getStore(String store_name) {
		return storeDao.getStore(store_name);
	}
	
	// 새 매장 정보 추가
	public boolean insertStore(Store store) {
		return storeDao.insert(store);
	}

	// 매장 정보 수정
	public boolean updateStore(Store store) {
		return storeDao.update(store);
	}
	
	// 매장 정보 삭제
	public boolean deleteStore(String store_name) {
		return storeDao.delete(store_name);
	}
}
