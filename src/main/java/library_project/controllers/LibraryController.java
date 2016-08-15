package library_project.controllers;

import library_project.models.Book;
import library_project.models.BookIsOutException;
import library_project.models.Library;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

/**
 * Created by pshek on 12/08/2016.
 */
@RestController
@RequestMapping("library")
public class LibraryController {
    private Library library;

    public LibraryController() {
        library = new Library();
    }
    public Iterator<Book> getAllAvailableBooks(){
        return library.getAllAvailableBooks();
    }

    public Iterator<Book> getAllUnavailableBooks() { return library.getAllUnavailableBooks(); }

    public Iterator<Book> getAllLateBooks() {
        return library.getAllLateBooks();
    }

    // try to get a book out for 2 week period
    public void requestBook(int bookId) throws BookIsOutException {
        library.requestBook(bookId);
    }

    public boolean isOut(int bookId) {
        return library.isOut(bookId);
    }

   public void returnBook(int reservationId) {
        library.returnBook(reservationId);
   }

}
