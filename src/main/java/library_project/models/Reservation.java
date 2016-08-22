package library_project.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "reservations")
public class Reservation {
    private Long reservationId;
    private Long bookId;

    private DateTimeFormatter dateFormatter;
    private String startDate;
    private String endDate;

    private boolean out;

    private Reservation() {
        dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    }

    public Reservation(Long bookId) {
        this();

        startDate = LocalDate.now().format(dateFormatter);
        endDate = LocalDate.now().plusWeeks(2).format(dateFormatter);

        this.bookId = bookId;

        out = true;
    }

    public Reservation(Long reservationId, Long bookId, String startDate, String endDate, boolean out) {
        this();
        this.bookId = bookId;
        setId(reservationId);
        setStartDate(startDate);
        setEndDate(endDate);
        setOut(out);

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
    public Long getId() {
        return reservationId;
    }

    public void setId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public boolean getOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) { this.bookId = bookId; }

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
