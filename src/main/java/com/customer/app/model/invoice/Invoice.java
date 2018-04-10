package com.customer.app.model.invoice;

import java.util.List;

public class Invoice {
	
	int id;
	List<InvoiceItem> items;
	
	public Invoice() { }
	
	public Invoice(int id, List<InvoiceItem> items) {
		this.id = id;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}
	
	
	
}
