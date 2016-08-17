package library_project.databasetools;

import library_project.models.Book;

import java.sql.*;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by pshek on 16/08/2016.
 */
public class DatabaseIterator<E> implements Iterator<E> {
    Connection conn;
    ResultSet rs;
    Statement stmt;
    boolean currRowHasBeenRead;
    boolean hasNext;
    RowParser<E> rowParser;

    public DatabaseIterator(DatabaseConnector databaseConnector, String queryString, RowParser<E> rowParser) {
        conn = null;
        stmt = null;
        rs = null;

        this.rowParser = rowParser;

        try {
            conn = databaseConnector.getConnection();

            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            hasNext = rs.next();
            currRowHasBeenRead = false;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
        }
    }

    public void close() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) { } // ignore
            rs = null;
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) { } // ignore
            stmt = null;
        }
    }

    @Override
    public boolean hasNext() {
        try {
            if (currRowHasBeenRead){ // if the current row has been read already, then move on
                hasNext = rs.next();
                currRowHasBeenRead = false;
            }
            // otherwise use previously saved value of hasNext (it does not change)

        } catch (SQLException e) { }

        // if we have reached the end, then close the connection
        if (!hasNext){
            close();
        }

        return hasNext;
    }

    @Override
    public E next() {
        try {
            if (currRowHasBeenRead){
                hasNext = rs.next();
            }
            currRowHasBeenRead = true;
            return rowParser.parse(rs);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
        }
        return null;
    }
}
