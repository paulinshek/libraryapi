package library_project.controllers;

import java.util.Iterator;

import library_project.repos.BookRepository;
import library_project.repos.NoEntityException;
import library_project.repos.Repository;
import library_project.models.Book;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class BooksController {
    private Repository<Book> bookRepo;

    public BooksController()
    {
        bookRepo = new BookRepository();
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

    @RequestMapping(value="/values",method=RequestMethod.POST)
    public void post(Book newBook)
    {
        bookRepo.add(newBook);
    }

    @RequestMapping(value="/values/{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) throws NoEntityException {
        bookRepo.remove(id);
    }

    @RequestMapping(value="api/values/{id}", method=RequestMethod.PUT)
    public void put(Book newBook)
    {
        // TODO
    }
}
