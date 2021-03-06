package kr.ac.hansung.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.model.Product;
import kr.ac.hansung.model.Stock;
import kr.ac.hansung.model.Store;
import kr.ac.hansung.service.ProductService;
import kr.ac.hansung.service.StockService;
import kr.ac.hansung.service.StoreService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private StockService stockService;
	@Autowired
	private ProductService productService;
	@Autowired
	private StoreService storeService;
	
	// DOSIRAK/으로 접근할 때
	@RequestMapping(value = "/")
	public String home(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		
		// 현재 관리 중인 매장 정보를 저장하기 위해 session을 사용
		if (session.getAttribute("store") == null) {
			List<Store> stores = storeService.getCurrent();
			model.addAttribute("stores", stores);
			return "setstore";
		}
		
		return "home";
	}
	
	// Android에서 데이터 통신을 위해 접근할 때
	@RequestMapping(value = "/data")
	public String data(Model model, HttpServletRequest request) {
		
		List<Stock> stocks = stockService.getCurrent();
		model.addAttribute("stocks", stocks);
		
		List<Product> products = productService.getCurrent();
		model.addAttribute("products", products);
		
		List<Store> stores = storeService.getCurrent();
		model.addAttribute("stores", stores);
		
		return "data";
	}
	
}
