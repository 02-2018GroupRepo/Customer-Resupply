package com.customer.app.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.customer.app.dao.ProductDao;
import com.customer.app.model.product.Product;



@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public static int randomQty() {
		return (int) Math.floor((Math.random() * (80 - 10) + 10));
	}
	
	public static BigDecimal randomPrice(BigDecimal price) {
		return new BigDecimal(Math.floor((Math.random() * 11) / 100));
	}
	
	public static List<Integer> randomProductIds(int num) {
		List<Integer> productIdList = new ArrayList<>();
		while (productIdList.size() < num) {
			productIdList.add((int) Math.floor(Math.random() * 60) + 1);
		}
		return productIdList;
	}
	
	@Async
	public CompletableFuture<ArrayList<Product>> getAllProducts() {
		ArrayList<Product> productList = (ArrayList<Product>) productDao.getAllProducts();
		return CompletableFuture.completedFuture(productList);
	}
	
}