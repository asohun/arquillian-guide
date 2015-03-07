package org.arquillian.example.bean;

import java.util.List;

import javax.ejb.Startup;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author asohun
 * @version 06.03.2015
 */
@Singleton
@Startup
public class LanguageDAO {

	/**
	 * 
	 */
	@PersistenceContext
	EntityManager em;

	/**
	 * @return
	 */
	public List<Language> listLanguages() {
		return em.createQuery("select l from Language l", Language.class)
				.getResultList();
	}

	/**
	 * @param language
	 */
	public void persist(Language language) {
		em.persist(language);
	}

}