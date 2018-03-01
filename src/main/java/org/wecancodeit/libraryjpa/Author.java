package org.wecancodeit.libraryjpa;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Author {

	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;

	@ManyToMany(mappedBy = "authors")
	private Collection<Book> books;

	public Author() {
	}

	public Author(String firstName, String lastName, Book... books) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.books = Arrays.asList(books);
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Collection<Book> getBooks() {
		return books;
	}

	@Override
	public int hashCode() {
		return ((Long) id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		return id == ((Author) obj).id;
	}

}
