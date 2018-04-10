package com.customer.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.app.dao.LocalDao;
import com.customer.app.model.invoice.Invoice;
import com.customer.app.model.product.Product;
import com.customer.app.service.InvoiceService;

@RestController
public class ProductController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private LocalDao localDao;
	
	@RequestMapping("/payment/{id}")
	@ResponseBody
	public List<Product> recievePayment(@PathVariable int id, @RequestBody Double payment) {
		System.out.println(localDao.validateTotal(id, payment));
		List<Product> productList = localDao.validateTotal(id, payment) ? localDao.getProductList(id) 
										    							: new ArrayList<Product>();
		return productList;
	}
	

}
