package library_project.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Created by pshek on 12/08/2016.
 */
public class Reservation {
    private int reservationId;
    private int bookId;

    private DateTimeFormatter dateFormatter;
    private String startDate;
    private String endDate;

    private boolean out;
    private static int count;

    public Reservation(int bookId) {
        dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        startDate = LocalDate.now().format(dateFormatter);
        endDate = LocalDate.now().plusWeeks(2).format(dateFormatter);

        this.reservationId = count;
        incrementCount();
        this.bookId = bookId;

        out = true;
    }

    public Reservation(int reservationId, int bookId, String startDate, String endDate, boolean out) {
        this.bookId = bookId;
        setId(reservationId);
        setStartDate(startDate);
        setEndDate(endDate);
        setOut(out);

    }

    public void returnBook() {
        out = false;
    }

    private synchronized static void incrementCount() {
        count++;
    }

    public boolean isLate() {
        //LocalDate today = LocalDate.now();
        //return (today.isAfter(LocalDate.parse(endDate, dateFormatter)));
        return false;
    }

    public int getId() {
        return reservationId;
    }

    public boolean getOut() {
        return out;
    }

    public int getBookId() {
        return bookId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    public void setId(int reservationId) {
        this.reservationId = reservationId;
    }
}
