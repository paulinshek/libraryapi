package library_project;

import library_project.controllers.BooksController;
import library_project.controllers.LibraryController;
import library_project.databasetools.DatabaseConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * http://www.tutorialspoint.com/spring/spring_java_based_configuration.htm
 */
//@Configuration
//public class LibraryConfig {
//
//    @Bean
//    public String dburl() {
//        return "jdbc:mysql://localhost:3306/test_schema";
//    }
//    @Bean
//    public String user() {
//        return "user";
//    }
//
//    @Bean
//    public String password() {
//        return "1234";
//    }
//    @Bean
//    public DatabaseConnector databaseConnector() {
//        return new DatabaseConnector(dburl(), user(), password());
//    }
//}
