package com.customer.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.customer.app.model.product.Product;
import com.customer.app.service.ProductService;

public class StoreProductSpec {
	
//	HashMap<Integer, Integer> testStoreList;
//
//	@Before
//	public void setup() {
//		// Inventory in Store
//		testStoreList = new HashMap<>();
//
//		testStoreList.put(1, 15);
//		testStoreList.put(3, 30);
//		testStoreList.put(56, 0);
//		testStoreList.put(45, 10);
//		testStoreList.put(25, 0);
//		testStoreList.put(9, 15);
//		testStoreList.put(22, 70);
//		testStoreList.put(54, 20);
//		testStoreList.put(58, 3);
//		testStoreList.put(21, 8);
//
//	}

	@Test
	public void storeTest() {
		 HashMap<Integer, Integer> testStoreList = new HashMap<>();
		
		 testStoreList.put(1, 15);
		 testStoreList.put(2, 30);
		 testStoreList.put(3, 0);
		 testStoreList.put(4, 10);
		 testStoreList.put(5, 0);
		 testStoreList.put(6, 15);
		 testStoreList.put(7, 70);
		 testStoreList.put(8, 20);
		 testStoreList.put(9, 3);
		 testStoreList.put(10, 8);

		// create post order object
		System.out.println();
		HashMap<Integer, Integer> orderMap = new HashMap<>();
		ArrayList<Integer> randomProductIDs = (ArrayList<Integer>) ProductService.randomProductIds(10);
		// set 20 random item id and quantity where the store quantity is not 0
		int i = 0;
		while (orderMap.size() < 8) {
			int productQuantity = testStoreList.get(randomProductIDs.get(i));
			if (productQuantity > 0) {
				orderMap.put(i, (int) (Math.floor(Math.random() * productQuantity) + 1));
			}
			i++;
		}
		assertTrue(orderMap.size() == 8);
		assertTrue(orderMap.get(1) > 0);
		assertTrue(orderMap.get(7) > 0);
		assertTrue(orderMap.get(8) > 0);
	}
}
