package library_project.controllers;

import library_project.databasetools.DatabaseConnector;
import library_project.models.Book;
import library_project.models.Reservation;
import library_project.repos.BookRepoDatabase;
import library_project.repos.BookRepository;
import library_project.repos.Repository;
import library_project.repos.ReservationRepoDatabase;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pshek on 19/08/2016.
 */
@Configuration
public class TestConfig {
    @Mock Repository<Reservation> reservationRepo;
    @Mock Repository<Book> bookRepo;

    public TestConfig() {
        MockitoAnnotations.initMocks(this);
    }

    @Bean
    public Repository<Book> bookRepo() {
        return bookRepo;
    }

    @Bean Repository<Reservation> reservationRepo() {
        return reservationRepo;
    }

}
