package library_project.repos;

import library_project.models.Book;
import library_project.models.Reservation;

import java.util.*;

/**
 * Created by pshek on 12/08/2016.
 */
public class ReservationRepository implements Repository<Reservation> {
    private List<Reservation> allReservations;

    public ReservationRepository() {
        allReservations = new ArrayList<>();
    }

    @Override
    public synchronized Reservation get(int reservationId) {
        Iterator<Reservation> resIt = allReservations.iterator();
        boolean found = false;
        Reservation currRes = null;

        while (resIt.hasNext() & !found){
            currRes = resIt.next();
            found = currRes.getReservationId() == reservationId;
        }

        return currRes;
    }

    @Override
    public synchronized Iterator<Reservation> getAll() {
        return allReservations.iterator();
    }

    @Override
    public synchronized void add(Reservation reservation) {
        allReservations.add(reservation);
    }

    @Override
    public synchronized void remove(int reservationId) {
        allReservations.remove(get(reservationId));
    }

}
