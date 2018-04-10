package com.customer.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.customer.app.dao.ProductDao;
import com.customer.app.service.InvoiceService;
import com.customer.app.service.ProductService;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class CustomerApplication {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
	
	@Scheduled(cron = "*/10 * * * * *")
	public void callDao() throws InterruptedException {
		invoiceService.sendInvoice(productService.getAllProducts(), "http://localhost:3000");
		invoiceService.sendInvoice(productService.getAllProducts(), "http://localhost:3001");
		invoiceService.sendInvoice(productService.getAllProducts(), "http://localhost:3002");
	}
	
}
