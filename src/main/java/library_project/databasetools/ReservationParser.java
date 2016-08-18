package library_project.databasetools;

import library_project.models.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Singleton
 */
public class ReservationParser implements RowParser<Reservation> {
    public static final ReservationParser INSTANCE = new ReservationParser();
    private ReservationParser() {}

    @Override
    public Reservation parse(ResultSet rs) throws SQLException {
        return new Reservation(rs.getInt("id"),
                rs.getInt("bookId"),
                rs.getString("startDate"),
                rs.getString("endDate"),
                rs.getBoolean("out"));
    }
}
