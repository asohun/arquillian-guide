package org.arquillian.example.basic.cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

/**
 * Represents a test implementation of the OrderRepository. Orders and items are
 * stored in memory.
 * 
 * @author asohun
 * @version 06.03.2015
 */
@Singleton
@Lock(LockType.READ)
public class SingletonOrderRepository implements OrderRepository {

	/**
	 * 
	 */
	private List<List<String>> orders;

	/**
	 * 
	 */
	@PostConstruct
	void initialize() {
		orders = new ArrayList<List<String>>();
	}

	/**
	 * @see org.arquillian.example.basic.cart.OrderRepository#addItems(List)
	 */
	@Override
	@Lock(LockType.WRITE)
	public void addItems(List<String> items) {
		orders.add(items);
	}

	/**
	 * @see org.arquillian.example.basic.cart.OrderRepository#getOrders()
	 */
	@Override
	public List<List<String>> getOrders() {
		return Collections.unmodifiableList(orders);
	}

	/**
	 * @see org.arquillian.example.basic.cart.OrderRepository#getOrderCount()
	 */
	@Override
	public int getOrderCount() {
		return orders.size();
	}

}