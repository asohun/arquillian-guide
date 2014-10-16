package org.arquillian.example.web;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("login.jsf")
public class LoginPage {

	@FindBy(id = "loginForm:userName")
	private WebElement userName;

	@FindBy(id = "loginForm:password")
	private WebElement password;

	@FindBy(id = "loginForm:login")
	private WebElement loginButton;

	public void login(String userName, String password) {
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
		guardHttp(loginButton).click();
	}
}