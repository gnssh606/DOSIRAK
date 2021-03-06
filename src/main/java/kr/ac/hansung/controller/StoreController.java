package kr.ac.hansung.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.model.Stock;
import kr.ac.hansung.model.Store;
import kr.ac.hansung.service.StockService;
import kr.ac.hansung.service.StoreService;

@Controller
public class StoreController {

	@Autowired
	private StoreService storeService;
	@Autowired
	private StockService stockService;
	
	// 관리 매장 변경 화면
	@RequestMapping("/changestore")
	public String showStores(Model model) {
		
		List<Store> stores = storeService.getCurrent();
		model.addAttribute("stores", stores);
		
		return "changestore";
	}
	
	// 새 매장 입력 화면
	@RequestMapping(value = "/newstore")
	public String newStore(Model model, HttpServletRequest request) {
		
		model.addAttribute("store", new Store());
		
		return "newstore";
	}

	// 새 매장 추가
	@RequestMapping(value = "/addstore")
	public String addStore(Model model, @Valid Store store, BindingResult result) {
		
		if (result.hasErrors())
			return "newstore";
		
		if (storeService.insertStore(store))
			model.addAttribute(store);
		
		return "insertstoresuccess";
	}
	
	// 관리 매장 변경
	@RequestMapping("/changestore/{store_name}")
	public String changeStore(Model model, HttpServletRequest request, @PathVariable ("store_name") String store_name) {
		
		HttpSession session = request.getSession();

		session.setAttribute("store", store_name);
		
		return "home";
	}
	
	// 매장 정보 수정을 위한 매장 목록 조회
	@RequestMapping("/searchstore")
	public String searchStore(Model model) {
		
		List<Store> stores = storeService.getCurrent();
		model.addAttribute("stores", stores);
		
		return "searchstore";
	}
	
	// 매장 정보 수정을 위한 초기값 입력
	@RequestMapping(value = "/updatestore/{store_name}")
	public String inputForUpdate(Model model, @PathVariable ("store_name") String store_name) {
		
		Store store = storeService.getStore(store_name);
		model.addAttribute("store", store);
		
		return "updatestore";
	}
	
	// 매장 정보 수정
	@RequestMapping(value = "/updatestore/{store_name}", method = RequestMethod.POST)
	public String update(Model model, HttpServletRequest request, @PathVariable ("store_name") String store_name, @Valid Store store, BindingResult result) {

		if (result.hasErrors()) 
			return "fail";
		
		HttpSession session = request.getSession();
		
		// 지점명을 변경한 경우
		if (!store_name.equals(store.getStore_name())) {
			// 기존에 해당 지점명을 가진 지점이 존재하지 않을 경우
			if (storeService.getStore(store.getStore_name()) == null) {
				storeService.insertStore(store);
				List<Stock> stocks = stockService.searchStocks(store_name, "");
				stockService.changeStore(stocks, store.getStore_name());
				storeService.deleteStore(store_name);
				if (session.getAttribute("store").equals(store_name))
					session.setAttribute("store", store.getStore_name());
				model.addAttribute("store", store);
			} else { // 기존에 해당 지점명을 가진 지점이 존재할 경우
				model.addAttribute("store", store);
				return "updatestorefail";
			}
				
		} else { // 지점명을 변경하지 않은 경우
			storeService.updateStore(store);
			model.addAttribute("store", store);
		}
		
		return "updatestoresuccess";
	}

	// 매장 정보 삭제
	@RequestMapping(value = "/deletestore/{store_name}", method = RequestMethod.POST)
	public String deleteStore(Model model, @PathVariable ("store_name") String store_name) {
		
		// 매장이 가진 재고 정보를 삭제한 후 매장 정보 삭제
		stockService.deleteStore(store_name);
		storeService.deleteStore(store_name);
		
		return "deletesuccess";
	}

}
