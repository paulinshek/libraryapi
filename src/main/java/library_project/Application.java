package library_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;

@SpringBootApplication
@ContextConfiguration
public class Application {

    public static void main(String[] args) { SpringApplication.run(Application.class, args); }
}
