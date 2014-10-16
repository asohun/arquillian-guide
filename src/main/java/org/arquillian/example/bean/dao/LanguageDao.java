package org.arquillian.example.bean.dao;

import java.util.List;

import javax.ejb.Startup;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.arquillian.example.bean.domain.Language;

@Singleton
@Startup
public class LanguageDao {

	@PersistenceContext
	EntityManager em;

	public List<Language> listLanguages() {
		return em.createQuery("select l from Language l").getResultList();
	}

	public void persist(Language language) {
		em.persist(language);
	}
}