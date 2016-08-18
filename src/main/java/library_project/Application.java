package library_project;

import library_project.controllers.LibraryController;
import library_project.databasetools.DatabaseConnector;
import library_project.databasetools.DatabaseIterator;
import library_project.models.Book;
import library_project.models.Reservation;
import library_project.repos.BookRepoDatabase;
import library_project.repos.Repository;
import library_project.repos.ReservationRepoDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@SpringBootApplication
public class Application {

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
        return new DatabaseConnector("jdbc:mysql://localhost:3306/test_schema", "root", "1234");
    }



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
