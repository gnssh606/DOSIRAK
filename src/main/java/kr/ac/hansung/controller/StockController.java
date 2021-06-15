package kr.ac.hansung.controller;

import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type;

import comparator.ProductComparator;
import kr.ac.hansung.model.Product;
import kr.ac.hansung.model.Stock;
import kr.ac.hansung.model.Store;
import kr.ac.hansung.service.ProductService;
import kr.ac.hansung.service.StockService;
import kr.ac.hansung.service.StoreService;

@Controller
public class StockController {

	@Autowired
	private StockService stockService;
	@Autowired
	private ProductService productService;
	@Autowired
	private StoreService storeService;
	
	// 테스트용 모든 데이터 조회
	@RequestMapping("/check")
	public String showStocks(Model model) {
		List<Stock> stocks = stockService.getCurrent();
		model.addAttribute("stocks", stocks);
		
		List<Product> products = productService.getCurrent();
		model.addAttribute("products", products);
		
		List<Store> stores = storeService.getCurrent();
		model.addAttribute("stores", stores);
		
		return "check";
	}
	
	// 제품 판매
	@RequestMapping(value = "/search")
	public String search(Model model, Stock stock, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Stock check = stockService.getStock(session.getAttribute("store").toString(), stock.getBarcode());
		if (check == null) {
			model.addAttribute("errorMsg", "제품을 찾을 수 없습니다");
		} else if (check.getCount() ==  0) {
			model.addAttribute("errorMsg", "재고가 없습니다");
		} else {
			Stock result = stockService.sellStock(session.getAttribute("store").toString(), stock.getBarcode());
			model.addAttribute("stock", result);
			Product result_Product = productService.getProductwithBarcode(stock.getBarcode());
			model.addAttribute("stockName", result_Product);
		}
		
		return "search";
	}
	
	// 재고 수정을 위한 재고 목록 조회
	@RequestMapping(value = "/searchstock")
	public String searchForUpdate(Model model, HttpServletRequest request, String type, String keyword) {
		
		if (type == null)
			return "searchstock";

		HttpSession session = request.getSession();
		String store = session.getAttribute("store").toString();
		List<Product> products = null;
		List<Stock> stocks = new ArrayList<Stock>();
		
		if (type.equals("barcode")) { // 바코드로 검색했을 경우
			products = productService.searchProducts(keyword);
		} else if (type.equals("name")) { // 제품명으로 검색했을 경우
			products = productService.searchProductsName(keyword);
		}
		
		Collections.sort(products, new ProductComparator());
		
		for (Product product : products) {
			stocks.add(stockService.getStock(store, product.getBarcode()));
		}

		model.addAttribute("products", products);
		model.addAttribute("stocks", stocks);
		return "searchstock";
	}
	
	// 재고 수정을 위한 초기값 입력
	@RequestMapping(value = "/updatestock/{barcode}")
	public String inputForUpdate(Model model, HttpServletRequest request, @PathVariable ("barcode") String barcode) {
		
		HttpSession session = request.getSession();
		
		Product product = productService.getProductwithBarcode(barcode);
		Stock stock = stockService.getStock(session.getAttribute("store").toString(), barcode);
		
		if (stock == null) {
			stock = new Stock();
			stock.setBarcode(barcode);;
			stock.setCount(0);
			stock.setStore_name(session.getAttribute("store").toString());					
		}
		
		model.addAttribute("product", product);
		model.addAttribute("stock", stock);
		
		return "updatestock";
	}
	
	// 재고 수정
	@RequestMapping(value = "/updatestock/{barcode}", method = RequestMethod.POST)
	public String update(Model model, HttpServletRequest request, @PathVariable ("barcode") String barcode, @Valid Stock stock, BindingResult result) {

		if (result.hasErrors())
			return "fail";
		
		HttpSession session = request.getSession();
		
		Product product = productService.getProductwithBarcode(barcode);
		Stock search = stockService.getStock(session.getAttribute("store").toString(), barcode);
		
		if (search == null)
			stockService.insertStock(stock);
		else
			stockService.updateStock(stock);
		
		model.addAttribute("product", product);
		model.addAttribute("stock", stock);
		
		return "stockupdatesuccess";
	}

	// 재고 삭제
	@RequestMapping(value = "/delete/{barcode}", method = RequestMethod.POST)
	public String delete(Model model, HttpServletRequest request, @PathVariable ("barcode") String barcode) {

		HttpSession session = request.getSession();
		
		stockService.deleteStock(session.getAttribute("store").toString(), barcode);
		
		return "deletesuccess";
	}

}
