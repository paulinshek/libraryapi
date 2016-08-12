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
    public Book get(int id) throws NoEntityException {
        Iterator<Book> bookIterator = bookRepository.iterator();
        Book currBook = new Book();
        boolean found = false;


        while (bookIterator.hasNext() & !found){
            currBook = bookIterator.next();
            found = currBook.getId() == id;
        }

        if (!found) throw new NoEntityException();

        return currBook;
    }

    @Override
    public Iterator<Book> getAll() {
        return bookRepository.iterator();
    }

    @Override
    public void add(Book book) {
        bookRepository.add(book);
    }

    @Override
    public void remove(int id) throws NoEntityException {
        bookRepository.remove(get(id));
    }
}
