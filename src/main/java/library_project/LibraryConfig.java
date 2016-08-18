package library_project;

import library_project.controllers.BooksController;
import library_project.controllers.LibraryController;
import library_project.databasetools.DatabaseConnector;
import library_project.models.Book;
import library_project.models.Reservation;
import library_project.repos.BookRepoDatabase;
import library_project.repos.Repository;
import library_project.repos.ReservationRepoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * http://www.tutorialspoint.com/spring/spring_java_based_configuration.htm
 */
@Configuration
public class LibraryConfig {
    @Bean
    public Repository<Reservation> reservationRepoDatabase() {
        return new ReservationRepoDatabase(databaseConnector());
    }

    @Bean
    public Repository<Book> bookRepoDatabase() {
        return new BookRepoDatabase(databaseConnector());
    }

    @Bean
    public DatabaseConnector databaseConnector() {
        return new DatabaseConnector("jdbc:mysql://localhost:3306/test_schema?autoReconnect=true&useSSL=false", "root", "1234");
    }
}
