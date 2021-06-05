package kr.ac.hansung.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import comparator.ProductComparator;
import kr.ac.hansung.model.Product;
import kr.ac.hansung.model.Stock;
import kr.ac.hansung.service.ProductService;
import kr.ac.hansung.service.StockService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private StockService stockService;
	
	@RequestMapping(value = "/newproduct")
	public String newProduct(Model model, HttpServletRequest request) {
		
		model.addAttribute("product", new Product());
		
		return "newproduct";
	}
	
	@RequestMapping(value = "/addproduct")
	public String addProduct(Model model, @Valid Product product, BindingResult result) {
		
		if (result.hasErrors())
			return "newproduct";
		
		if (productService.insertProduct(product))
			model.addAttribute(product);
		
		return "insertsuccess";
	}
	
	@RequestMapping(value = "/searchproduct")
	public String searchForUpdate(Model model, String type, String keyword) {
		
		if (type == null)
			return "searchproduct";
		
		List<Product> products = null;
		
		if (type.equals("barcode")) {
			products = productService.searchProducts(keyword);
		} else if (type.equals("name")) {
			products = productService.searchProductsName(keyword);
		}

		Collections.sort(products, new ProductComparator());
		model.addAttribute("products", products);
		
		return "searchproduct";
	}
	
	@RequestMapping(value = "/updateproduct/{barcode}")
	public String inputForUpdate(Model model, HttpServletRequest request, @PathVariable ("barcode") String barcode) {
		
		Product product = productService.getProductwithBarcode(barcode);
		model.addAttribute("product", product);
		
		return "updateproduct";
	}
	
	@RequestMapping(value = "/updateproduct/{barcode}", method = RequestMethod.POST)
	public String update(Model model, @PathVariable ("barcode") String barcode, @Valid Product product, BindingResult result) {

		if (result.hasErrors())
			return "fail";
		
		// 바코드를 변경한 경우
		if (!barcode.equals(product.getBarcode())) {
			// 기존에 해당 바코드를 가진 제품이 존재하지 않을 경우
			if (productService.getProductwithBarcode(product.getBarcode()) == null) {
				productService.insertProduct(product);
				List<Stock> stocks = stockService.searchStocksWithBarcode(barcode);
				stockService.changeBarcode(stocks, product.getBarcode());
				productService.deleteProduct(barcode);
				model.addAttribute("product", product);
			} else { // 기존에 해당 바코드를 가진 제품이 존재할 경우
				model.addAttribute("product", product);
				return "updatefail";
			}
				
		} else { // 바코드를 변경하지 않은 경우
			productService.updateProduct(product);
			model.addAttribute("product", product);
		}
		
		return "updatesuccess";
	}
	
	@RequestMapping(value = "/deleteproduct/{barcode}", method = RequestMethod.POST)
	public String deleteProduct(Model model, HttpServletRequest request, @PathVariable ("barcode") String barcode) {
		
		stockService.deleteProduct(barcode);
		productService.deleteProduct(barcode);
		
		return "deletesuccess";
	}
}
