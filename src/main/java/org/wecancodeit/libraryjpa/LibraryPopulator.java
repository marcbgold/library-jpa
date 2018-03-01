package org.wecancodeit.libraryjpa;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LibraryPopulator implements CommandLineRunner {

	@Resource
	private GenreRepository genreRepo;

	@Resource
	private BookRepository bookRepo;

	@Resource
	private AuthorRepository authorRepo;

	@Override
	public void run(String... args) throws Exception {
		Genre fiction = genreRepo.save(new Genre("Fiction"));
		Genre nonfiction = genreRepo.save(new Genre("Nonfiction"));

		Author firstAuthor = authorRepo.save(new Author("Big", "McLargeHuge"));
		Author secondAuthor = authorRepo.save(new Author("Boaty", "McBoatface"));

		bookRepo.save(new Book(nonfiction, "Head First Java", firstAuthor));
		bookRepo.save(new Book(fiction, "Fullmetal Alchemist", firstAuthor, secondAuthor));
	}

}
