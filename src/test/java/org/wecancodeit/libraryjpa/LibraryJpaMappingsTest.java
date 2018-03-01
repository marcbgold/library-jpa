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

	// @Resource
	// private AuthorRepository authorRepo;

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

	// @Test
	// public void shouldSaveAndLoadTopic() {
	// Topic topic = topicRepo.save(new Topic("its name"));
	// long topicId = topic.getId();
	//
	// entityManager.flush(); // forces jpa to hit the db when we try to find it
	// entityManager.clear();
	//
	// topic = topicRepo.findOne(topicId);
	// assertThat(topic.getName(), is("its name"));
	// }
	//
	// @Test
	// public void shouldGenerateTopicId() {
	// Topic topic = topicRepo.save(new Topic("its name"));
	// long topicId = topic.getId();
	//
	// entityManager.flush(); // forces jpa to hit the db when we try to find it
	//
	// assertThat(topicId, is(greaterThan(0L)));
	// }
	//
	// @Test
	// public void shouldEstablishCourseToTopicsRelationships() {
	// // topic is not the owner, so we save these first
	// Topic java = topicRepo.save(new Topic("Java"));
	// Topic ruby = topicRepo.save(new Topic("Ruby"));
	//
	// Course course = new Course("OO Languages", java, ruby);
	// course = courseRepo.save(course);
	// long ooLanguagesId = course.getId();
	//
	// entityManager.flush();
	// entityManager.clear();
	//
	// course = courseRepo.findOne(ooLanguagesId);
	// assertThat(course.getTopics(), containsInAnyOrder(java, ruby));
	//
	// }
	//
	// @Test
	// public void shouldEstablishTopicToCoursesRelationship() {
	// Topic topic = topicRepo.save(new Topic("Ruby"));
	// long topicId = topic.getId();
	//
	// Course ooLanguages = new Course("OO Languages", topic);
	// ooLanguages = courseRepo.save(ooLanguages);
	//
	// Course scriptingLanguages = new Course("Scripting Languages", topic);
	// scriptingLanguages = courseRepo.save(scriptingLanguages);
	//
	// entityManager.flush();
	// entityManager.clear();
	//
	// topic = topicRepo.findOne(topicId);
	// assertThat(topic.getCourses(), containsInAnyOrder(ooLanguages,
	// scriptingLanguages));
	// }
}
