package bookstore.Project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bookstore.Project.domain.Book;
import bookstore.Project.domain.BookRepository;
import bookstore.Project.domain.Category;
import bookstore.Project.domain.CategoryRepository;
import bookstore.Project.BookstoreApplication;
import bookstore.Project.domain.Book;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookrepository, CategoryRepository categoryrepository) {
		return (args) -> {
			log.info("Saving categories...");
			Category category1 = new Category("Dystopia");
			categoryrepository.save(category1);
			Category category2 = new Category("Scifi");
			categoryrepository.save(category2);
			
			log.info("save book");
			bookrepository.save(new Book("1984", "George Orwell", 1949, "9780451524935", 20, category1));
			bookrepository.save(new Book("Animal Farm", "George Orwell", 1945, "9780451526342", 5, category1));
			bookrepository.save(new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", 1968, "9781780220383", 40, category2));
			
			log.info("fetch all books");
			for (Book book: bookrepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
