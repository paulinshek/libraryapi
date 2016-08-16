package library_project.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by pshek on 12/08/2016.
 */
public class Reservation {
    private int reservationId;
    private int bookId;
    private Calendar startDate;
    private Calendar endDate;
    private boolean out;
    private static int COUNT;

    public Reservation(int bookId) {
        startDate = Calendar.getInstance();

        endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_YEAR, 14);

        this.reservationId = COUNT;
        COUNT++;
        this.bookId = bookId;

        out = true;
    }

    public Reservation(int reservationId, int bookId, String startDate, String endDate, boolean out) {
        this.reservationId = reservationId;
        this.bookId = bookId;

        this.startDate = Calendar.getInstance();
        this.endDate = Calendar.getInstance();

        try{
            this.startDate.setTime(new SimpleDateFormat("dd-MMM-yyyy").parse(startDate));
            this.endDate.setTime(new SimpleDateFormat("dd-MMM-yyyy").parse(endDate));
        } catch (Exception e) {}

        this.out = out;
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

    public boolean isOut() {
        return out;
    }

    public int getBookId() {
        return bookId;
    }
}
