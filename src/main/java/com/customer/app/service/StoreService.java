package com.customer.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.customer.app.model.product.Order;

@Service
public class StoreService {
	
	@Autowired
	private RestTemplate restTemplate;

	public Hashtable<Integer, Integer> getStoreInventory() {
		return restTemplate.getForObject("url/inventory/all", Hashtable.class);
	}

	public Hashtable<Integer, Integer> createOrder(Hashtable<Integer, Integer> storeInventory) {
		Hashtable<Integer, Integer> orderMap = new Hashtable<>();
	
		System.out.println(storeInventory.get("1"));
		ArrayList<Integer> randomProductIDs = (ArrayList<Integer>) ProductService.randomProductIds(3);
		// set 20 random item id and quantity where the store quantity is not 0
		int i = 0;
		while (orderMap.size() < 8) {
		try {
			
			
			int productQuantity = storeInventory.get(i + 1);
			if (productQuantity > 0) {
				orderMap.put(i, (int) (Math.floor(Math.random() * productQuantity) + 1));
				Order order = new Order(i, (int) (Math.floor(Math.random() * productQuantity) + 1));
				restTemplate.postForObject("url/inventory/purchase", new Order(), Order.class);
			}
			i++;
		} catch (Exception e) {
			System.out.println(e);
		}
		}
		System.out.println("ORDER MAP:" + orderMap);
		return orderMap;
	}
	public void sendOrder() {
		System.out.println("SEND ORDER");
		Hashtable<Integer, Integer> storeInventory = this.getStoreInventory();
		Hashtable<Integer, Integer> orderMap = this.createOrder(storeInventory);
		
		
		restTemplate.postForObject("url/inventory/purchase", orderMap, HashMap.class);
	}
}
