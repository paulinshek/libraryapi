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
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@SpringBootApplication
@ContextConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
