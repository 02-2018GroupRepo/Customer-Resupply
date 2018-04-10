package com.customer.app;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.customer.app.dao.ProductDao;
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
	
	@Scheduled(cron = "*/5 * * * * *")
	public void callDao() throws InterruptedException {
		invoiceService.sendInvoice(productService.getAllProducts(), "http://localhost:3000");
//		invoiceService.sendInvoice(productService.getAllProducts(), "http://localhost:3001");
//		invoiceService.sendInvoice(productService.getAllProducts(), "http://localhost:3002");
//		HashMap<Integer, Integer> test = new HashMap<>();
//		test.put(1, 1);
//		test.put(2,2);
//		restTemplate.postForObject("http://localhost:3000", test, HashMap.class);
//		System.out.println(restTemplate.getForObject("http://localhost:3000", HashMap.class));
		
	}
	
}
