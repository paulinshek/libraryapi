package library_project.models;

import library_project.repos.*;

import java.util.*;

/**
 * Created by pshek on 15/08/2016.
 */
public class Library {
    private Repository<Reservation> allReservations;
    private Repository<Book> allBooks;

    public Library(Repository<Book> allBooks, Repository<Reservation> allReservations) {
        this.allBooks = allBooks;
        this.allReservations = allReservations;
    }

    public Iterator<Reservation> getAllReservations(){
        return allReservations.getAll();
    }

    public Long requestBook(Long bookId) throws BookIsOutException {
        Reservation newReservation = null;
        synchronized (this) {
            // check that the book is *not* out
            if (!isOut(bookId)) {
                newReservation = new Reservation(bookId);
                allReservations.add(newReservation);
                return newReservation.getId();
            }
        }
        throw new BookIsOutException();
    }

    public void returnBook(Long reservationId) {
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
            if (currRes.getOut()){
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
            if (currRes.getOut()) { // if reservation is active then book is unavailable
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
     * Finds all reservations related to bookId
     */
    private Iterator<Reservation> findReservations(Long bookId) {
        Repository<Reservation> foundReservations = new ReservationRepository();
        Iterator<Reservation> reservationIterator = allReservations.getAll();
        Reservation currReservation = null;

        // iterate through all reservations
        while (reservationIterator.hasNext()){
            currReservation = reservationIterator.next();
            // does the book id match? if so, then add to found reservations
            if (currReservation.getId() == bookId){
                foundReservations.add(currReservation);
            }
        }

        return foundReservations.getAll();
    }

    public boolean isOut(Long bookId) {
        Iterator<Reservation> relevantReservations = findReservations(bookId);
        boolean isOut = false;

        while (relevantReservations.hasNext() & !isOut){
            isOut = relevantReservations.next().getOut();
        }
        return isOut;
    }
}
