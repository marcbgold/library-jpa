package org.wecancodeit.libraryjpa;

public class Genre {

	private long id;
	private String genre;

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
}
