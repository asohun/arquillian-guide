package org.arquillian.example.bean.dao;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.arquillian.example.bean.domain.Language;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class LanguageDaoTest {

	@Inject
	private LanguageDao languagedao;

	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addClasses(Language.class, LanguageDao.class)
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(jar.toString(true));
		return jar;
	}

	@Test
	public void should_be_deployed() {
		Assert.assertNotNull(languagedao);
	}

	@Test
	@Transactional
	// @UsingDataSet("languages.xml")
	@ShouldMatchDataSet("languages.xml")
	public void init() throws NotSupportedException, SystemException,
			SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException {
		Language java = new Language();
		java.setName("English");
		languagedao.persist(java);

		Language ruby = new Language();
		ruby.setName("French");
		languagedao.persist(ruby);

		// int size = languagedao.listLanguages().size();
		// Assert.assertEquals(size, 2);
	}

}