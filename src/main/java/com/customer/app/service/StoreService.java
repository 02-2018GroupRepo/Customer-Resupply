package com.customer.app.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StoreService {
	
	@Autowired
	private RestTemplate restTemplate;

	public HashMap<Integer, Integer> getStoreInventory() {
		return restTemplate.getForObject("http://localhost:3000", HashMap.class);
	}

	public HashMap<Integer, Integer> createOrder() {
		HashMap<Integer, Integer> orderMap = new HashMap<>();
		HashMap<Integer, Integer> storeInventory = this.getStoreInventory();
		ArrayList<Integer> randomProductIDs = (ArrayList<Integer>) ProductService.randomProductIds(3);
		// set 20 random item id and quantity where the store quantity is not 0
		int i = 0;
		while (orderMap.size() < 8) {
			int productQuantity = storeInventory.get(randomProductIDs.get(i));
			if (productQuantity > 0) {
				orderMap.put(i, (int) (Math.floor(Math.random() * productQuantity) + 1));
			}
			i++;
		}
		return orderMap;
	}
	public void sendOrder() {
		restTemplate.postForObject("http://localhost:3000", this.createOrder(), HashMap.class);
	}
}
