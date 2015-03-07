package org.arquillian.example.basic.welcome;

import java.io.PrintStream;

import javax.inject.Inject;

/**
 * @author asohun
 * @version 06.03.2015
 */
public class Greeter {

	/**
	 * 
	 */
	private PhraseBuilder phraseBuilder;

	/**
	 * @param phraseBuilder
	 */
	@Inject
	public Greeter(PhraseBuilder phraseBuilder) {
		this.phraseBuilder = phraseBuilder;
	}

	/**
	 * @param to
	 * @param name
	 */
	public void greet(PrintStream to, String name) {
		to.println(createGreeting(name));
	}

	/**
	 * @param name
	 * @return
	 */
	public String createGreeting(String name) {
		return phraseBuilder.buildPhrase("hello", name);
	}

}