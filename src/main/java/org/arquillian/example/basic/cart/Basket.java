package org.arquillian.example.basic.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/**
 * @author asohun
 * @version 06.03.2015
 */
@SessionScoped
public class Basket implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The list of items in the basket
	 */
	private List<String> items;

	@EJB
	private OrderRepository orderRepository;

	/**
	 * Initialize the list of items
	 */
	@PostConstruct
	void initialize() {
		items = new ArrayList<String>();
	}

	/**
	 * @param item
	 *            the string representing the item to add to an order
	 */
	public void addItem(String item) {
		items.add(item);
	}

	/**
	 * Add the items to an order
	 */
	public void placeOrder() {
		orderRepository.addItems(items);
		items.clear();
	}

	/**
	 * @return the integer representing the number of items
	 */
	public int getItemCount() {
		return items.size();
	}

	/**
	 * @return the list of items
	 */
	public List<String> getItems() {
		return Collections.unmodifiableList(items);
	}

}