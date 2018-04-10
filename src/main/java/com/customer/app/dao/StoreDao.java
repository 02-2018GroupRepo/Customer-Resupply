package com.customer.app.dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.customer.app.model.product.Product;

@Repository
public class StoreDao {
		@Autowired
		private static final Logger log = LoggerFactory.getLogger(StoreDao.class);
		
		@Autowired
		private RestTemplate restTemplate;
		
		public Map<Integer , Integer> getProductsFromStore () {
			return (Map<Integer, Integer>) restTemplate.getForObject("some store url", Map.class);
		}
}
