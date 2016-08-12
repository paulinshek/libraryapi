package library_project.repos;

import library_project.models.Book;
import library_project.models.Reservation;

import java.util.*;

/**
 * Created by pshek on 12/08/2016.
 */
public class ReservationRepository implements Repository<Reservation> {
    private List<Reservation> allReservations;
    private Repository<Book> allBooks;

    public ReservationRepository() {
        allReservations = new ArrayList<>();
        allBooks = new FilledRepository();
    }

    @Override
    public Reservation get(int reservationId) throws NoEntityException {
        return null;
    }

    @Override
    public Iterator<Reservation> getAll() {
        return allReservations.iterator();
    }

    @Override
    // TODO I WAS HERE LAST
    public void add(Reservation reservation) {
        // check that the book is not out
        if (findReservations(reservation.getBookId()).size() == 0) {
            allReservations.add(reservation);
        } else {
            throw new Exception();
        }

    }

    @Override
    public void remove(int id) throws NoEntityException {

    }

    public Iterable<Book> getAllAvailableBooks(){
        return null;
    }

    public Iterable<Book> getAllUnavailableBooks() {
        return null;
    }

    public Iterable<Book> getAllLateBooks() {
        return null;
    }

    // TODO: should this be static?
    private Iterator<Integer> findBooks(Iterator<Reservation> reservations) {
        Set<Integer> foundBooks = new HashSet<>();

        while (reservations.hasNext()) {
            foundBooks.add(reservations.next().getBookId());
        }
        return foundBooks.iterator();
    }

    private Iterator<Integer> findReservations(int bookId) {
        List<Integer> foundReservations = new ArrayList<Integer>();
        Iterator<Reservation> reservationIterator = getAll();
        Reservation currReservation = null;

        while (reservationIterator.hasNext()){
            currReservation = reservationIterator.next();
            if (currReservation.getReservationId() == bookId){
                foundReservations.add(currReservation.getBookId());
            }
        }

        return foundReservations.iterator();
    }
}
