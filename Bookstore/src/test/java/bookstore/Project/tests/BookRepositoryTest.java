package bookstore.Project.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import bookstore.Project.domain.Book;
import bookstore.Project.domain.BookRepository;
import bookstore.Project.domain.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


//@RunWith(SpringRunner.class)  //JUnit4
@ExtendWith(SpringExtension.class)  // JUnit5 eli Jupiter
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("1984");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("George Orwell");
    }
    
    @Test
    public void createSearchAndDeleteNewBook() {
    	// create and save a new book
    	Book book = new Book("Discourses", "Epictetus", 108, "0-674-99145-1", 20, null);
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    	
    	// search for the created book by its id
      	Long bookId = book.getId();
    	repository.findById(bookId);
    	assertThat(bookId).isNotNull();
    	
    	// delete the same book after successful previous tests
    	repository.deleteById(bookId);
    	assertThat(bookId).isNull();
    }
}