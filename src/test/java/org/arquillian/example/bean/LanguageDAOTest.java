package org.arquillian.example.bean;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author asohun
 * @version 06.03.2015
 */
@RunWith(Arquillian.class)
public class LanguageDAOTest {

	/**
	 * 
	 */
	@Inject
	private LanguageDAO languagedao;

	/**
	 * @return
	 */
	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
		jar.addPackage(Language.class.getPackage());
		jar.addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
		jar.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		return jar;
	}

	/**
	 * 
	 */
	@Test
	public void should_be_deployed() {
		Assert.assertNotNull(languagedao);
	}

	/**
	 * 
	 */
	@Test
	@Transactional
	@ShouldMatchDataSet("dataset/languages.xml")
	public void should_match_dataset() {
		Language java = new Language();
		java.setName("English");
		languagedao.persist(java);

		Language ruby = new Language();
		ruby.setName("French");
		languagedao.persist(ruby);
	}

	/**
	 * 
	 */
	@Test
	@Transactional
	@UsingDataSet("dataset/languages.xml")
	public void init() {
		int size = languagedao.listLanguages().size();
		Assert.assertEquals(2, size);
	}
}