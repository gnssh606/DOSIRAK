<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.*" %>
<%@page import="kr.ac.hansung.model.Stock" %>
<%@page import="kr.ac.hansung.model.Product" %>
<%@page import="kr.ac.hansung.model.Store" %>
<%
	JSONObject jsonMain = new JSONObject();
 	
 	List<Stock> stocks = (List<Stock>)request.getAttribute("stocks");
 	JSONArray jArray = new JSONArray();
 	
 	for (Stock stock : stocks) {
 	 	JSONObject jObject = new JSONObject();
 	 	
	 	jObject.put("id", stock.getId());
	 	jObject.put("barcode", stock.getBarcode());
	 	jObject.put("count", stock.getCount());
	 	jObject.put("store_name", stock.getStore_name());
	 	
	 	jArray.add(jObject);
 	}
 	
 	jsonMain.put("stock", jArray);
 	
 	List<Product> products = (List<Product>)request.getAttribute("products");
 	jArray = new JSONArray();
 	
 	for (Product product : products) {
 	 	JSONObject jObject = new JSONObject();
 	 	
	 	jObject.put("id", product.getId());
	 	jObject.put("product_name", product.getProduct_name());
	 	jObject.put("barcode", product.getBarcode());
	 	jObject.put("price", product.getPrice());
	 	jObject.put("category", product.getCategory());
	 	
	 	jArray.add(jObject);
 	}

 	jsonMain.put("product", jArray);
 	
 	List<Store> stores = (List<Store>)request.getAttribute("stores");
 	jArray = new JSONArray();
 	
 	for (Store store : stores) {
 	 	JSONObject jObject = new JSONObject();
 	 	
	 	jObject.put("id", store.getId());
	 	jObject.put("store_name", store.getStore_name());
	 	jObject.put("location", store.getLocation());
	 	
	 	jArray.add(jObject);
 	}
 	
 	jsonMain.put("store", jArray);
 	
	out.print(jsonMain.toString());
%>