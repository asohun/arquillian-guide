package org.arquillian.example.web;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@RunWith(Arquillian.class)
public class LoginScreenGrapheneTest {
	private static final String WEBAPP_SRC = "src/main/webapp";

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		//
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
		System.out.println(war.toString(true));
		return war;
	}

	public static WebArchive test() {
		return ShrinkWrap
				.create(WebArchive.class, "login.war")
				.addClasses(Credentials.class, User.class,
						LoginController.class)
				.merge(ShrinkWrap.create(GenericArchive.class)
						.as(ExplodedImporter.class).importDirectory(WEBAPP_SRC)
						.as(GenericArchive.class), "/",
						Filters.include(".*\\.xhtml$"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(
						new StringAsset("<faces-config version=\"2.0\"/>"),
						"faces-config.xml");
	}

	@Drone
	private WebDriver browser;

	@ArquillianResource
	private URL deploymentUrl;

	@FindBy(id = "loginForm:userName")
	private WebElement userName;

	@FindBy(id = "loginForm:password")
	private WebElement password;

	@FindBy(id = "loginForm:login")
	private WebElement loginButton;

	@FindBy(tagName = "li")
	private WebElement facesMessage;

	@FindByJQuery("p:visible")
	private WebElement signedAs;

	@FindBy(css = "input[type=submit]")
	private WebElement whoAmI;

	@Test
	public void should_login_successfully() {
		browser.get("http://localhost:8080/login/login.xhtml");

		userName.sendKeys("demo");
		password.sendKeys("demo");

		Graphene.guardHttp(loginButton).click();

		Assert.assertEquals("Welcome", facesMessage.getText().trim());
		whoAmI.click();

		Graphene.waitAjax().until().element(signedAs).is().present();
		Assert.assertTrue(signedAs.getText().contains("demo"));
	}
}