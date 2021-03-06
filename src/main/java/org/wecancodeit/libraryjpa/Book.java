package org.wecancodeit.libraryjpa;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Genre genre;
	private String title;

	@ManyToMany
	private Collection<Author> authors;

	public Book() {
	}

	public Book(Genre genre, String title, Author... authors) {
		this.genre = genre;
		this.title = title;
		this.authors = new HashSet<>(Arrays.asList(authors));
	}

	public long getId() {
		return id;
	}

	public Genre getGenre() {
		return genre;
	}

	public String getTitle() {
		return title;
	}

	public Collection<Author> getAuthors() {
		return authors;
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

		return id == ((Book) obj).id;
	}

}
