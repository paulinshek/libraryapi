package library_project.controllers;

import java.util.Iterator;

import library_project.repos.BookRepository;
import library_project.repos.FilledRepository;
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
        bookRepo = new FilledRepository();
    }

    @RequestMapping(value="/books",method=RequestMethod.GET)
    public Iterator<Book> get()
    {
        return bookRepo.getAll();
    }

    @RequestMapping(value="/values/{id}",method=RequestMethod.GET)
    public Book get(@PathVariable("id") int id) throws NoEntityException {
        return bookRepo.get(id);
    }

    @RequestMapping(value="/values",method=RequestMethod.POST)
    public void post(@RequestBody Book newBook)
    {
        bookRepo.add(newBook);
    }

    @RequestMapping(value="/values/{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) throws NoEntityException {
        bookRepo.remove(id);
    }

    @RequestMapping(value="/values/{id}", method=RequestMethod.PUT)
    public void put(@RequestBody Book newBook, @PathVariable("id") int id)
    {
        bookRepo.add(newBook);
        newBook.setId(id);
    }
}