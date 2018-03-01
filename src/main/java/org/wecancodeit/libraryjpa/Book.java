package org.wecancodeit.libraryjpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Genre genre;
	// private Author author;

	private String title;

	public Book() {
	}

	public Book(Genre genre, String title) { // , String author) {
		this.genre = genre;
		this.title = title;
		// this.author = author;
	}

	public long getId() {
		return id;
	}

	public Genre getGenre() {
		return genre;
	}

	// public String getAuthor() {
	// return author;
	// }

	public String getTitle() {
		return title;
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
