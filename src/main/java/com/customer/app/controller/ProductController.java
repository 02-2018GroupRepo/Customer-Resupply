package com.customer.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.app.model.invoice.Invoice;
import com.customer.app.service.InvoiceService;

@RestController
public class ProductController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping("/")
	public HttpStatus test(@RequestBody Invoice invoice) {
		System.out.println(invoice.getId());
		return HttpStatus.CREATED;
	}
	

}
