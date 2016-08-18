package library_project.repos;

import library_project.databasetools.DatabaseConnector;
import library_project.databasetools.DatabaseIterator;
import library_project.databasetools.ReservationParser;
import library_project.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by pshek on 16/08/2016.
 */
//@ContextConfiguration("/Beans.xml")
public class ReservationRepoDatabase implements Repository<Reservation> {
    DatabaseConnector databaseConnector;
//    public ReservationRepoDatabase(){
//        ApplicationContext context = new ClassPathXmlApplicationContext("/Beans.xml");
//        databaseConnector = (DatabaseConnector) context.getBean("databaseConnector");
//    }
    public ReservationRepoDatabase(DatabaseConnector databaseConnector){
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Reservation get(int id) {
        DatabaseIterator<Reservation> resIterator = new DatabaseIterator<Reservation>(
                databaseConnector,
                "SELECT * FROM reservations WHERE id =" + id,
                ReservationParser.INSTANCE);
        Reservation res = null;
        if (resIterator.hasNext()){
            res = resIterator.next();
        }
        resIterator.close();
        return res;
    }

    @Override
    public Iterator<Reservation> getAll() {
        return new DatabaseIterator<Reservation>(
                databaseConnector,
                "SELECT * FROM reservations",
                ReservationParser.INSTANCE);
    }

    @Override @Resource
    public void add(Reservation reservation) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = databaseConnector.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO reservations " +
                    "VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, reservation.getId());
            pstmt.setInt(2, reservation.getBookId());
            pstmt.setString(3, reservation.getStartDate());
            pstmt.setString(4, reservation.getEndDate());
            pstmt.setBoolean(5, reservation.getOut());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
        }
    }

    @Override
    public void remove(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = databaseConnector.getConnection();

            pstmt = conn.prepareStatement("DELETE FROM reservations WHERE id = " + id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
        }
    }
}
