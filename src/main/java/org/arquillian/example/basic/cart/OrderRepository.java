package org.arquillian.example.basic.cart;

import java.util.List;
import javax.ejb.Local;

/**
 * @author asohun
 * @version 06.03.2015
 */
@Local
public interface OrderRepository {

	/**
	 * @param orders
	 *            the list of items to add to an order
	 */
	void addItems(List<String> items);

	/**
	 * @return the list of orders
	 */
	List<List<String>> getOrders();

	/**
	 * 
	 * @return the integer representing the number of orders
	 */
	int getOrderCount();
	
}