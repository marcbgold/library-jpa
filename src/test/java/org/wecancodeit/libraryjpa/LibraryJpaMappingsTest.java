package org.wecancodeit.libraryjpa;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class LibraryJpaMappingsTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private GenreRepository genreRepo;

	@Resource
	private BookRepository bookRepo;

	@Resource
	private AuthorRepository authorRepo;

	@Test
	public void shouldSaveAndLoadCourse() {
		Genre underTest = genreRepo.save(new Genre("test"));
		long genreId = underTest.getId();

		entityManager.flush();
		entityManager.clear();

		underTest = genreRepo.findOne(genreId);
		assertThat(underTest.getGenre(), is("test"));
	}

	@Test
	public void shouldSaveBookToGenreRelationship() {

		Genre genre = genreRepo.save(new Genre("test"));
		long genreId = genre.getId();

		Book first = new Book(genre, "first test");
		bookRepo.save(first);

		Book second = new Book(genre, "second test");
		bookRepo.save(second);

		entityManager.flush();
		entityManager.clear();

		genre = genreRepo.findOne(genreId);
		assertThat(genre.getBooks(), containsInAnyOrder(first, second));
	}

	@Test
	public void shouldSaveAndLoadAuthor() {
		Author underTest = authorRepo.save(new Author("first", "last"));
		long AuthorId = underTest.getId();

		entityManager.flush();
		entityManager.clear();

		underTest = authorRepo.findOne(AuthorId);
		assertThat(underTest.getFirstName(), is("first"));
	}

	@Test
	public void shouldEstablishAuthorsToBooksRelationship() {
		Genre genre = genreRepo.save(new Genre("test"));
		Author firstAuthor = authorRepo.save(new Author("first", "last"));
		Author secondAuthor = authorRepo.save(new Author("first", "last"));
		Book book = bookRepo.save(new Book(genre, "Java", firstAuthor, secondAuthor));
		long bookId = book.getId();

		entityManager.flush();
		entityManager.clear();

		book = bookRepo.findOne(bookId);
		assertThat(book.getAuthors(), containsInAnyOrder(firstAuthor, secondAuthor));
	}

	@Test
	public void shouldEstablishBooksToAuthorsRelationship() {
		Genre genre = genreRepo.save(new Genre("test"));
		Author author = authorRepo.save(new Author("first", "last"));
		Book firstBook = bookRepo.save(new Book(genre, "Java", author));
		Book secondBook = bookRepo.save(new Book(genre, "Ruby", author));
		long authorId = author.getId();

		entityManager.flush();
		entityManager.clear();

		author = authorRepo.findOne(authorId);
		assertThat(author.getBooks(), containsInAnyOrder(firstBook, secondBook));
	}
}
