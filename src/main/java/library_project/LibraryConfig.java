package library_project;

import library_project.databasetools.DataAccess;
import library_project.models.Book;
import library_project.models.Library;
import library_project.models.Reservation;
import library_project.repos.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * http://www.tutorialspoint.com/spring/spring_java_based_configuration.htm
 */
@Configuration
public class LibraryConfig {
    @Bean
    public Repository<Reservation> reservationRepoDatabase() {
        return new ReservationRepoHibernate(databaseConnector());
    }

    @Bean
    public Library library() {
        return new Library(bookRepoDatabase(), reservationRepoDatabase());
    }

    @Bean
    public Repository<Book> bookRepoDatabase() {
        return new BookRepoHibernate(databaseConnector());
    }

    @Bean
    public DataAccess databaseConnector() {
        DataAccess dao = new DataAccess();
        return dao;
    }
}
