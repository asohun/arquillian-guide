package org.arquillian.example.web;

/**
 * @author asohun
 * @version 28/05/2013
 */
public class User {

	/**
	 * The String representing the user's login
	 */
	private String username;

	/**
	 * Default constructor
	 */
	public User() {

	}

	/**
	 * @param username
	 *            the String to set
	 */
	public User(String username) {
		this.username = username;
	}

	/**
	 * @return {@link org.arquillian.example.web.User#username} the String to
	 *         return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the String to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

}