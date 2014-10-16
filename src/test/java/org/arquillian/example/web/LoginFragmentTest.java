package org.arquillian.example.web;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Arquillian.class)
public class LoginFragmentTest {
	private static final String WEBAPP_SRC = "src/main/webapp";

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "login.war")
				.addClasses(Credentials.class, User.class,
						LoginController.class)
				.addAsWebResource(new File(WEBAPP_SRC, "login.xhtml"))
				.addAsWebResource(new File(WEBAPP_SRC, "home.xhtml"))
				.addAsWebInfResource(
						new File(WEBAPP_SRC + "/WEB-INF", "web.xml"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(
						new StringAsset("<faces-config version=\"2.0\"/>"),
						"faces-config.xml");
		return war;
	}

	@Page
	private HomePage homePage;

	@Drone
	private WebDriver browser;

	@Test
	public void should_login_successfully(@InitialPage LoginPage loginPage) {
		browser.get("http://localhost:8080/login/login.xhtml");

		loginPage.login("demo", "demo");
		homePage.assertOnHomePage();

		Assert.assertEquals(homePage.getUserName(),
				"You are signed in as demo.");
	}
}