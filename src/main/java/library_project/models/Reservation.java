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
    private SimpleDateFormat dateFormat;

    public Reservation(int bookId) {
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        startDate = Calendar.getInstance();

        endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_YEAR, 14);

        this.reservationId = COUNT;
        COUNT++;
        this.bookId = bookId;

        out = true;
    }

    public Reservation(int reservationId, int bookId, String startDate, String endDate, boolean out) {
        this.bookId = bookId;
        setReservationId(reservationId);
        setStartDate(startDate);
        setEndDate(endDate);
        setOut(out);
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

    public String getStartDate() {
        return dateFormat.format(startDate.getTime());
    }

    public String getEndDate() {
        return dateFormat.format(endDate.getTime());
    }

    public void setStartDate(String startDate) {
        this.startDate = Calendar.getInstance();

        try{
            this.startDate.setTime(dateFormat.parse(startDate));
        } catch (Exception e) {
            System.out.println("Failed to parse start date");
        }
    }

    public void setEndDate(String endDate) {
        this.endDate = Calendar.getInstance();

        try{
            this.endDate.setTime(dateFormat.parse(endDate));
        } catch (Exception e) {
            System.out.println("Failed to parse end date");
        }
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
}
