package com.customer.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.customer.app.dao.LocalDao;
import com.customer.app.dao.Payment;
import com.customer.app.model.invoice.Invoice;
import com.customer.app.model.product.Order;
import com.customer.app.model.product.Product;
import com.customer.app.service.InvoiceService;

@RestController
public class ProductController {

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private LocalDao localDao;

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/payment/{id}")
	@ResponseBody
	public List<Product> recievePayment(@PathVariable int id, @RequestBody Payment paymentObject) {

		List<Product> productList = localDao.validateTotal(id, paymentObject.getPaymentForProduct().doubleValue())
				? localDao.getProductList(id)
				: new ArrayList<Product>();
		return productList;

	}

}
