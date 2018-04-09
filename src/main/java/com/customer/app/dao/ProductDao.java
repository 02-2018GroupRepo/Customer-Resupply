package com.customer.app.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.customer.app.model.product.Product;

@Repository
public class ProductDao {
	
	@Autowired
	private static final Logger log = LoggerFactory.getLogger(ProductDao.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<Product> getAllProducts() {
		List<Product> allProducts = jdbcTemplate.query("Select * from product",
				new BeanPropertyRowMapper<Product>(Product.class));
		log.info("Products: " + allProducts);
		return allProducts;
	}
}
