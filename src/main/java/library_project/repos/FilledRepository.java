package library_project.repos;

import library_project.models.Book;

import java.util.Iterator;

/**
 * Created by pshek on 12/08/2016.
 */
public class FilledRepository implements Repository<Book> {
    private BookRepository bookRepo;

    public FilledRepository() {
        bookRepo = new BookRepository();
        // Fill it with books
        bookRepo.add(new Book("ABC123","Brave New World","Huxley","12-08-2016"));
        bookRepo.add(new Book("ASF234","Harry Potter","J K Rowling","12-04-2001"));
        bookRepo.add(new Book("DFG432","A Long Way to a Small Angry Planet","","23-05-2014"));
    }

    @Override
    public Book get(int id) {
        return bookRepo.get(id);
    }

    @Override
    public Iterator<Book> getAll() {
        return bookRepo.getAll();
    }

    @Override
    public void add(Book entity) {
        bookRepo.add(entity);
    }

    @Override
    public void remove(int id) {
        bookRepo.remove(id);
    }
}
