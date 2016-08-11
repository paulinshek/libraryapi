package library_project.controllers;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

import library_project.Repos.NoEntityException;
import library_project.Repos.Repository;
import library_project.models.Book;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class BooksController {

    private Repository<Book> bookRepo;

    public BooksController(Repository<Book> bookRepository)
    {
        bookRepo = bookRepository;
    }

    @RequestMapping("/books")
    public Iterator<Book> get()
    {
        return bookRepo.getAll();
    }

    @RequestMapping("/values/{id}")
    public Book get(@PathVariable("id") int id) throws NoEntityException {
        return bookRepo.get(id);
    }

    @RequestMapping(value="api/values",method=RequestMethod.POST)
    public void post(Book newBook)
    {
        bookRepo.add(newBook);
    }

    @RequestMapping(value="/values/{id}",method=RequestMethod.DELETE)
    public void delete(int id) throws NoEntityException {
        bookRepo.remove(id);
    }

    // PUT api/values/{int}
    public void put(Book newBook)
    {
        // TODO
    }
}
