package library_project.models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by pshek on 12/08/2016.
 */
public class Reservation {
    private int reservationId;
    private int bookId;
    private Calendar startDate;
    private Calendar endDate;
    private boolean out;

    public Reservation(int reservationId, int bookId) {
        startDate = Calendar.getInstance();

        endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_YEAR, 14);

        this.reservationId = reservationId;
        this.bookId = bookId;

        out = true;
    }

    public void returnBook() {
        out = false;
    }

    public boolean isLate() {
        return Calendar.getInstance().compareTo(endDate) > 0;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getBookId() {
        return bookId;
    }
}
