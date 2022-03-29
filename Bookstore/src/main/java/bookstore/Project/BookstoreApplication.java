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
import bookstore.Project.domain.User;
import bookstore.Project.domain.UserRepository;
import bookstore.Project.BookstoreApplication;
import bookstore.Project.domain.Book;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookrepository, CategoryRepository categoryrepository, UserRepository userrepository) {
		return (args) -> {
			log.info("Saving categories...");
			Category category1 = new Category("Dystopia");
			categoryrepository.save(category1);
			Category category2 = new Category("Scifi");
			categoryrepository.save(category2);
			Category category3 = new Category("Philosophy");
			categoryrepository.save(category3);
			
			log.info("save book");
			bookrepository.save(new Book("1984", "George Orwell", 1949, "9780451524935", 20, category1));
			bookrepository.save(new Book("Animal Farm", "George Orwell", 1945, "9780451526342", 5, category1));
			bookrepository.save(new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", 1968, "9781780220383", 40, category2));
			
			log.info("Create admin(admin) and user1(mrbrown) and user2(mrblack)");
			User admin = new User("admin", "$2a$10$i00QBusgixhWN4ZmeDOPHu6AfoYXfXDNEDEc8coL2RaOgeiJFpKlG", "ADMIN");
			User user1 = new User("mrBrown", "$2a$10$BTM1r5WmxZxuvlLPxtuhkOwyb3xGyTKTFZAa2ztEF2iK.N8Ikm7Tq", "USER");
			User user2 = new User("mrBlack", "$2a$10$DNBd1NQi3//0DsgwMc1s2uliiuGP74ANc0gdrN.dC1ghjGCulmbxO", "USER");
			
			userrepository.save(user1);
			userrepository.save(user2);
			userrepository.save(admin);
			
			log.info("fetch all books");
			for (Book book: bookrepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
