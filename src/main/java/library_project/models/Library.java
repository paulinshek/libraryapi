package library_project.models;

import library_project.repos.BookRepository;
import library_project.repos.FilledRepository;
import library_project.repos.Repository;
import library_project.repos.ReservationRepository;

import java.util.*;

/**
 * Created by pshek on 15/08/2016.
 */
public class Library {
    private ReservationRepository allReservations;
    private Repository<Book> allBooks;
    public Library() {
        allBooks = new FilledRepository();
        allReservations  = new ReservationRepository();
    }
    public void requestBook(int bookId) throws BookIsOutException {
        // check that the book is *not* out
        if (!isOut(bookId)) {
            allReservations.add(new Reservation(bookId));
        } else {
            throw new BookIsOutException();
        }
    }

    public void returnBook(int reservationId) {
        allReservations.remove(reservationId);
    }

    public Iterator<Book> getAllAvailableBooks(){
        Iterator<Reservation> allResIt = allReservations.getAll();
        Repository<Book> availableBooks = new BookRepository();
        Reservation currRes = null;

        Iterator<Book> allBooksIt = allBooks.getAll();
        while(allBooksIt.hasNext()) availableBooks.add(allBooksIt.next()); // copy all books over

        while(allResIt.hasNext()){
            currRes = allResIt.next();
            if (currRes.isOut()){
                availableBooks.remove(currRes.getBookId()); // if book is out, then remove from return object
            }
        }

        return availableBooks.getAll();
    }

    public Iterator<Book> getAllUnavailableBooks() {
        Iterator<Reservation> allResIt = allReservations.getAll();
        Repository<Book> unavailableBooks  = new BookRepository();
        Reservation currRes = null;

        while (allResIt.hasNext()) {
            currRes = allResIt.next();
            if (currRes.isOut()) { // if reservation is active then book is unavailable
                Book outBook = allBooks.get(currRes.getBookId());
                unavailableBooks.add(outBook);
            }
        }

        return unavailableBooks.getAll();
    }

    public Iterator<Book> getAllLateBooks() {
        Iterator<Reservation> allResIt = allReservations.getAll();
        Repository<Book> lateBooks  = new BookRepository();
        Reservation currRes = null;

        while (allResIt.hasNext()) {
            currRes = allResIt.next();
            if (currRes.isLate()) { // if reservation is active then book is unavailable
                Book outBook = allBooks.get(currRes.getBookId());
                lateBooks.add(outBook);
            }
        }

        return lateBooks.getAll();
    }

    /*
    Finds all reservations related to bookId
     */
    private Iterator<Reservation> findReservations(int bookId) {
        Repository<Reservation> foundReservations = new ReservationRepository();
        Iterator<Reservation> reservationIterator = allReservations.getAll();
        Reservation currReservation = null;

        // iterate through all reservations
        while (reservationIterator.hasNext()){
            currReservation = reservationIterator.next();
            // does the book id match? if so, then add to found reservations
            if (currReservation.getReservationId() == bookId){
                foundReservations.add(currReservation);
            }
        }

        return foundReservations.getAll();
    }

    public boolean isOut(int bookId) {
        Iterator<Reservation> relevantReservations = findReservations(bookId);
        boolean isOut = false;

        while (relevantReservations.hasNext() & !isOut){
            isOut = relevantReservations.next().isOut();
        }
        return isOut;
    }
}
