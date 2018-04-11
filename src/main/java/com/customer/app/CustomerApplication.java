package com.customer.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.customer.app.dao.ProductDao;
import com.customer.app.model.product.Order;
import com.customer.app.model.product.Product;
import com.customer.app.service.InvoiceService;
import com.customer.app.service.ProductService;
import com.customer.app.service.StoreService;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class CustomerApplication {

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private ProductService productService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Scheduled(cron = "1 * * * * *")
	public void callDao() throws InterruptedException {
		
		ArrayList<Product> products = new ArrayList<>();
		Product product = new Product();
		product.setDescription("");
		product.setId(59);
		product.setRetail_price(new BigDecimal(5.00));
		product.setWholesale_price(new BigDecimal(2.29));

		Product product1 = new Product();
		product1.setDescription("");
		product1.setId(58);
		product1.setRetail_price(new BigDecimal(5.00));
		product1.setWholesale_price(new BigDecimal(2.29));

		Product product2 = new Product();
		product2.setDescription("");
		product2.setId(57);
		product2.setRetail_price(new BigDecimal(5.00));
		product2.setWholesale_price(new BigDecimal(2.29));

		Product product3 = new Product();
		product3.setDescription("");
		product3.setId(56);
		product3.setRetail_price(new BigDecimal(5.00));
		product3.setWholesale_price(new BigDecimal(2.29));

		products.add(product);
		products.add(product1);
		products.add(product2);
		products.add(product3);

		restTemplate.postForObject("http://18.236.70.220:5500/receive/inventory", products, List.class);

		
	}
	
	@Scheduled(cron = "*/5 * * * * *")
	public void test() throws InterruptedException {
		Order order = new Order();
		order.setId((int) Math.floor(Math.random() * 55) + 1);
		order.setQuantity(1);
		restTemplate.postForObject("http://18.236.70.220:3500/inventory/purchase", order, Order.class);
	}

}
