package com.customer.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.customer.app.dao.ProductDao;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class CustomerApplication {
	
	@Autowired
	private ProductDao dao;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
	
	@Scheduled(cron = "*/5 * * * * *")
	public void callDao() {
		dao.getAllProducts();
	}
	
}
