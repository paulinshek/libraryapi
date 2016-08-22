package library_project.repos;

import library_project.models.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pshek on 11/08/2016.
 */
public class BookRepository implements Repository<Book> {
    private List<Book> bookRepository;

    public BookRepository() {
        bookRepository = new ArrayList<Book>();
    }

    @Override
    public Book get(Long id) {
        Iterator<Book> bookIterator = bookRepository.iterator();
        Book currBook = null;
        boolean found = false;

        while (bookIterator.hasNext() & !found) {
            currBook = bookIterator.next();
            found = currBook.getId().equals(id);
        }

        return currBook;
    }

    @Override
    public Iterator<Book> getAll() {
        return bookRepository.iterator();
    }

    @Override
    public void add(Book book) {
        book.setId(null);
        bookRepository.add(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.remove(get(id));
    }
}
