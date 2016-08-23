package library_project.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "reservations")
public class Reservation {
    private long reservationId;
    private long bookId;

    private DateTimeFormatter dateFormatter;
    private String startDate;
    private String endDate;

    private boolean out;

    private Reservation() {
        dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    }

    public Reservation(long bookId) {
        this();

        startDate = LocalDate.now().format(dateFormatter);
        endDate = LocalDate.now().plusWeeks(2).format(dateFormatter);

        this.bookId = bookId;

        out = true;
    }

    public Reservation(long reservationId, long bookId, String startDate, String endDate, boolean out) {
        this();
        this.bookId = bookId;
        setId(reservationId);
        setStartDate(startDate);
        setEndDate(endDate);
        setIsOut(out);

    }

    public void returnBook() {
        out = false;
    }

    @Transient
    public boolean isLate() {
        LocalDate today = LocalDate.now();
        return (today.isAfter(LocalDate.parse(endDate, dateFormatter)));
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment")
    public long getId() {
        return reservationId;
    }

    public void setId(long reservationId) {
        this.reservationId = reservationId;
    }


    public boolean getIsOut() {
        return out;
    }

    public void setIsOut(boolean out) {
        this.out = out;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) { this.bookId = bookId; }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
