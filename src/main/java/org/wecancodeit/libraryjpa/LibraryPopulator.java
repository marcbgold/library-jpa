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

	@Override
	public void run(String... args) throws Exception {
		Genre fiction = new Genre("Fiction");
		fiction = genreRepo.save(fiction);
		Genre nonfiction = genreRepo.save(new Genre("Nonfiction"));

		bookRepo.save(new Book(nonfiction, "Head First Java"));
		bookRepo.save(new Book(fiction, "Fullmetal Alchemist"));
	}

}
