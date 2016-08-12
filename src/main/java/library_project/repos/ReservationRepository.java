package library_project.repos;

import library_project.models.Reservation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pshek on 12/08/2016.
 */
public class ReservationRepository implements Repository<Reservation> {
    private List<Reservation> allReservations;

    public ReservationRepository() {
        allReservations = new ArrayList<Reservation>();
    }

    @Override
    public Reservation get(int id) throws NoEntityException {
        return null;
    }

    @Override
    public Iterator<Reservation> getAll() {
        return null;
    }

    @Override
    public void add(Reservation entity) {

    }

    @Override
    public void remove(int id) throws NoEntityException {

    }
}
