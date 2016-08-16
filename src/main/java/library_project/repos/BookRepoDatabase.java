package library_project.repos;

import library_project.models.Book;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Iterator;
import java.util.Properties;

/**
 * Implement book repository but with a database backend
 */
public class BookRepoDatabase implements Repository<Book> {
    private String dburl;
    private Properties connectionProps;

    public BookRepoDatabase() {
        dburl = "jdbc:mysql://localhost:3306/test_schema";

        connectionProps =  new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "1234");
    }

    @Override
    public Book get(int id) {
        BookIteratorDatabase bookIterator = new BookIteratorDatabase(dburl, connectionProps, "SELECT * FROM books WHERE id =" + id);
        Book res = null;
        if (bookIterator.hasNext()){
            res = bookIterator.next();
        }
        bookIterator.close();
        return res;
    }

    @Override
    public Iterator<Book> getAll() {
        return new BookIteratorDatabase(dburl, connectionProps, "SELECT * FROM books");
    }

    @Override
    public void add(Book book) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(dburl, connectionProps);

            pstmt = conn.prepareStatement("INSERT INTO books (id, isbn, title, author, publishDate)" +
                    "VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, book.getId());
            pstmt.setString(2, book.getIsbn());
            pstmt.setString(3, book.getTitle());
            pstmt.setString(4, book.getAuthor());
            pstmt.setString(5, book.getPublishDate());

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
            conn = DriverManager.getConnection(dburl, connectionProps);

            pstmt = conn.prepareStatement("DELETE FROM books WHERE id = " + id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
        }
    }
}
