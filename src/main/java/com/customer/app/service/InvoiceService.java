package com.customer.app.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.customer.app.model.invoice.Invoice;
import com.customer.app.model.invoice.InvoiceItem;
import com.customer.app.model.product.Product;

@Service
public class InvoiceService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Async
	public void sendInvoice(CompletableFuture<ArrayList<Product>> products, String url) throws InterruptedException {
		ArrayList<InvoiceItem> invoiceItemList = new ArrayList<InvoiceItem>();
		products.thenAccept((arr) -> {
			arr.stream()
			   .filter(item -> ProductService.randomProductIds().indexOf(item.getId()) != -1)
			   .forEach(item -> {
				item.setWholesale_price(ProductService.randomPrice(item.getWholesale_price()));
				invoiceItemList.add(new InvoiceItem(item, ProductService.randomQty()));
			});
			restTemplate.postForEntity(url, new Invoice(1, invoiceItemList), Invoice.class);
		});

	}
}
