package org.arquillian.example.web;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;

import org.jboss.arquillian.graphene.GrapheneElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(tagName = "li")
	private WebElement facesMessage;

	@FindBy(className = "whoami")
	private WebElement signedAs;

	@FindBy(css = "input[type=submit]")
	private GrapheneElement whoAmI;

	public void assertOnHomePage() {
		Assert.assertEquals("We should be on home page", "Welcome",
				getMessage());
	}

	public String getMessage() {
		return facesMessage.getText().trim();
	}

	public String getUserName() {
		if (signedAs.findElements(By.tagName("p")).isEmpty()) {
			whoAmI();
		}
		return signedAs.findElement(By.tagName("p")).getText();
	}

	private void whoAmI() {
		guardAjax(whoAmI).click();
	}
}