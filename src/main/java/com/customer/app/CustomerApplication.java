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
 		storeService.sendOrder();
 	}
	
 	@Scheduled(cron = "*/5 * * * * *")
 	public void test() throws InterruptedException {
 		invoiceService.sendInvoice(productService.getAllProducts(), ${url});
 	}

}
