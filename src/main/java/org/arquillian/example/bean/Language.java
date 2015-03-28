package org.arquillian.example.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author asohun
 * @version 28/03/2015
 */
@Entity
@XmlRootElement
public class Language implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8070852807411895989L;

	/**
	 * The Long representing the primary key of the Language entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	/**
	 * The String representing the name of the language
	 */
	@Column
	private String name;

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
			return id.equals(((Language) that).id);
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (name != null && !name.trim().isEmpty())
			result += "name: " + name;
		return result;
	}

	/**
	 * @return {@link org.arquillian.example.bean.Language#id} the Long to
	 *         return
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
	 * @return {@link org.arquillian.example.bean.Language#name} the String to
	 *         return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the string to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}