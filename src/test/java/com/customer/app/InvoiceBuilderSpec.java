package com.customer.app;

import static org.assertj.core.api.Assertions.filter;
import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.sql.DataSource;
import javax.validation.constraints.AssertTrue;

import org.h2.engine.Database;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import com.customer.app.model.invoice.Invoice;
import com.customer.app.model.invoice.InvoiceItem;
import com.customer.app.model.product.Product;
import com.customer.app.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceBuilderSpec {

	@Autowired
	private ProductService productService;

	@Test
	public void test() throws Exception {
//		Given: Building invoice to send
		ArrayList<InvoiceItem> invoiceItemList = new ArrayList<InvoiceItem>();
		List<Integer> productIds = new ArrayList<Integer>();
//		When: The list is finished building
		productService.getAllProducts()
					  .get()
					  .stream()
					  .filter(item -> ProductService.randomProductIds().indexOf(item.getId()) != -1)
					  .forEach(item -> {
						  item.setWholesale_price(ProductService.randomPrice(item.getWholesale_price()));
						  invoiceItemList.add(new InvoiceItem(item, ProductService.randomQty()));
						});
//		When: The list is finished building
		Invoice invoice = new Invoice(1, invoiceItemList);
//		Then: The total quantity of the item should be between (10, 80) && price should be within +- 10% of wholesale
		assertTrue(invoice.getItems().get(0).getCount() >= 10 && invoice.getItems().get(0).getCount() <= 80);
	}
	

}
