package org.arquillian.example.basic.welcome;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

/**
 * @author asohun
 * @version 06.03.2015
 */
public class PhraseBuilder {

	/**
	 * 
	 */
	private Map<String, String> templates;

	/**
	 * 
	 */
	@PostConstruct
	public void initialize() {
		templates = new HashMap<String, String>();
		templates.put("hello", "Hello, {0}!");
	}

	/**
	 * @param id
	 * @param args
	 * @return
	 */
	public String buildPhrase(String id, Object... args) {
		return MessageFormat.format(templates.get(id), args);
	}

}