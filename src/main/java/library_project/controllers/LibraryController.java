package library_project.controllers;

import library_project.models.Book;
import library_project.models.BookIsOutException;
import library_project.models.Library;
import library_project.models.Reservation;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Iterator;

/**
 * Created by pshek on 12/08/2016.
 */
@RestController
@RequestMapping("api/library")
public class LibraryController {
    private Library library;

    public LibraryController() {
        library = new Library();
    }

    /*
     * Get all available books
     */
    @RequestMapping(value="/group/availableBooks", method=RequestMethod.GET)
    public Iterator<Book> getAllAvailableBooks(){
        return library.getAllAvailableBooks();
    }

    /*
     * Get all unavailableBooks
     */
    @RequestMapping(value="/group/unavailableBooks", method=RequestMethod.GET)
    public Iterator<Book> getAllUnavailableBooks() { return library.getAllUnavailableBooks(); }

    /*
     * Get all late books
     */
    @RequestMapping(value="/group/lateBooks", method=RequestMethod.GET)
    public Iterator<Book> getAllLateBooks() {
        return library.getAllLateBooks();
    }

    /*
     * Gets the status of a book
     */
    @RequestMapping(value="/reservations/status/{bookId}", method=RequestMethod.GET)
    public String isOut(@PathVariable("bookId") int bookId) {
        if (library.isOut(bookId)) return "Out";
        return "In";
    }

    /*
     * Return a book with reservationId
     */
    @RequestMapping(value="/reservations/{reservationId}", method=RequestMethod.DELETE)
    public String returnBook(@PathVariable("reservationId") int reservationId) {
        library.returnBook(reservationId);
        return "Book has now been returned";
    }

    /*
     * Reserve a book with bookId, returns the reservationId
     */
    @RequestMapping(value="/reservations/book/{bookId}", method=RequestMethod.PUT)
    public int requestBook(@PathVariable("bookId") int bookId) throws BookIsOutException {
        return library.requestBook(bookId);
    }

    /*
     * List all reservations
     */
    @RequestMapping(value="/reservations", method=RequestMethod.GET)
    public Iterator<Reservation> getAllReservations() {
        return library.getAllReservations();
    }
}
