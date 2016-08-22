package library_project.repos;

import library_project.databasetools.DataAccess;
import library_project.models.Book;

import java.util.Iterator;

public class BookRepoHibernate implements Repository<Book> {
    DataAccess hibernateAccess;

    public BookRepoHibernate(DataAccess hibernateAccess){
        this.hibernateAccess = hibernateAccess;
    }

    @Override
    public Book get(int id) {
        return  hibernateAccess.getBook(id);
    }

    @Override
    public Iterator<Book> getAll() {
        return hibernateAccess.getAllBooks();
    }

    @Override
    public void add(Book entity) {
        hibernateAccess.addBook(entity);
    }

    @Override
    public void remove(int id) {
        hibernateAccess.removeBook(id);
    }
}
