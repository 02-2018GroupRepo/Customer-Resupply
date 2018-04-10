package com.customer.app.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.customer.app.dao.LocalDao;
import com.customer.app.dao.ProductDao;
import com.customer.app.model.invoice.Invoice;
import com.customer.app.model.invoice.InvoiceItem;
import com.customer.app.model.product.Product;

@Service
public class InvoiceService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);
	
	@Autowired
	private LocalDao localDao;
	
	@Async
	public void sendInvoice(CompletableFuture<ArrayList<Product>> products, String url) throws InterruptedException {
		ArrayList<InvoiceItem> invoiceItemList = new ArrayList<InvoiceItem>();
		products.thenAccept((arr) -> {
			arr.stream()
			   .filter(item -> ProductService.randomProductIds(10).indexOf(item.getId()) != -1)
			   .forEach(item -> {
				item.setWholesale_price(ProductService.randomPrice(item.getWholesale_price()));
				invoiceItemList.add(new InvoiceItem(item, ProductService.randomQty()));
			});
			int invoiceId = (int) Math.floor(Math.random() * 100000000);
			localDao.addInvoice(invoiceId, invoiceItemList);
			restTemplate.postForEntity(url, new Invoice(invoiceId, invoiceItemList), Invoice.class);
		});

	}
	
	
}
