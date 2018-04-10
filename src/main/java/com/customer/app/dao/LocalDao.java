package com.customer.app.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.customer.app.model.invoice.InvoiceItem;
import com.customer.app.model.product.Product;

@Repository
public class LocalDao {

	public HashMap<Integer, List<InvoiceItem>> invoiceMap = new HashMap<Integer, List<InvoiceItem>>();

	public void addInvoice(int id, List<InvoiceItem> productList) {
		this.invoiceMap.put(id, productList);
	}

	public double calculateTotal(int id) {
		ArrayList<InvoiceItem> invoiceItems = (ArrayList<InvoiceItem>) this.invoiceMap.get(id);
		double total = 0;
		for (InvoiceItem item : invoiceItems) {
			total += item.getCount() * item.getProduct().getWholesale_price().doubleValue();
		}

		return total;
	}

	public List<Product> getProductList(int id) {
		ArrayList<Product> productList = new ArrayList<Product>();
		ArrayList<InvoiceItem> invoiceItems = (ArrayList<InvoiceItem>) this.invoiceMap.get(id);
		for (InvoiceItem item : invoiceItems) {
			productList.add(item.getProduct());
		}
		return productList;
	}

	public boolean validateTotal(int id, double payment) {
		System.out.println(this.invoiceMap.size());
		if (this.calculateTotal(id) <= payment) {
			return true;
		} else {
			return false;
		}
	}
}
