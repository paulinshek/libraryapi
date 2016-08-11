package library_project.controllers;

import java.util.concurrent.atomic.AtomicLong;

import library_project.models.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

//    @RequestMapping("/greeting")
//    public Book greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Book(counter.incrementAndGet(),
//                            String.format(template, name));
//    }

}
