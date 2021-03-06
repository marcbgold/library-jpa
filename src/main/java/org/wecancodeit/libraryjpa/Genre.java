package org.wecancodeit.libraryjpa;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Genre {

	@Id
	@GeneratedValue
	private long id;
	private String genre;

	@OneToMany(mappedBy = "genre")
	Collection<Book> books;

	public Genre() {
	}

	public Genre(String genre) {
		this.genre = genre;
	}

	public long getId() {
		return id;
	}

	public String getGenre() {
		return genre;
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

		return id == ((Genre) obj).id;
	}
}
