package org.arquillian.example.basic.welcome;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This test can be run using the arquillian-weld-ee-embedded profile.
 * 
 * @author asohun
 * @version 06.03.2015
 */
@RunWith(Arquillian.class)
public class GreeterTest {

	/**
	 * @return
	 */
	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
		jar.addPackage(Greeter.class.getPackage());
		jar.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		return jar;
	}

	/**
	 * 
	 */
	@Inject
	private Greeter greeter;

	/**
	 * 
	 */
	@Test
	public void should_create_greeting() {
		Assert.assertEquals("Hello, Earthling!",
				greeter.createGreeting("Earthling"));
		greeter.greet(System.out, "Earthling");
	}

}