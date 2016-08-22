package library_project.repos;

import library_project.databasetools.DataAccess;
import library_project.models.Reservation;

import java.util.Iterator;

/**
 * Created by pshek on 22/08/2016.
 */
public class ReservationRepoHibernate implements Repository<Reservation> {
    private DataAccess hibernateAccess;

    public ReservationRepoHibernate(DataAccess hibernateAccess){
        this.hibernateAccess = hibernateAccess;
    }

    @Override
    public Reservation get(int id) {
        return hibernateAccess.getReservation(id);
    }

    @Override
    public Iterator<Reservation> getAll() {
        return hibernateAccess.getAllReservations();
    }

    @Override
    public void add(Reservation entity) {
        hibernateAccess.addReservation(entity);
    }

    @Override
    public void remove(int id) {
        hibernateAccess.removeReservation(id);
    }
}
