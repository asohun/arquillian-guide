package org.arquillian.example.web;

import javax.enterprise.inject.Model;

/**
 * @author asohun
 * @version 28/03/2015
 */
@Model
public class Credentials {

	/**
	 * The String representing the login of the user
	 */
	private String username;

	/**
	 * The String representing the user's password
	 */
	private String password;

	/**
	 * @return {@link org.arquillian.example.web.Credentials#username} the
	 *         String to return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the string to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return {@link org.arquillian.example.web.Credentials#password} the
	 *         String to return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the String to return
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}