package org.arquillian.example.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * @author asohun
 * @version 28/03/2015
 */
@Entity
public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7865131753074472564L;

	/**
	 * The Long representing the primary key of the Game entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	/**
	 * The integer representing the version of the Game entity
	 */
	@Version
	@Column(name = "version")
	private int version = 0;

	/**
	 * The String representing the title of the game
	 */
	@Column
	private String title;

	/**
	 * Default constructor
	 */
	public Game() {

	}

	/**
	 * @param title
	 *            the String representing the title to set
	 */
	public Game(String title) {
		this.title = title;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}

		if (that == null) {
			return false;
		}

		if (getClass() != that.getClass()) {
			return false;
		}

		if (id != null) {
			return id.equals(((Game) that).id);
		}

		return super.equals(that);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		}

		return super.hashCode();
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (title != null && !title.trim().isEmpty())
			result += "title: " + title;
		return result;
	}

	/**
	 * @return {@link org.arquillian.example.jpa.Game#id} the Long to return
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the Long to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return {@link org.arquillian.example.jpa.Game#version} the integer to
	 *         set
	 */
	public int getVersion() {
		return this.version;
	}

	/**
	 * @param version
	 *            the integer to return
	 */
	public void setVersion(final int version) {
		this.version = version;
	}

	/**
	 * @return {@link org.arquillian.example.jpa.Game#title} the String to
	 *         return
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title
	 *            the String to set
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

}