package library_project.repos;

import library_project.databasetools.BookParser;
import library_project.databasetools.DatabaseConnector;
import library_project.databasetools.DatabaseIterator;
import library_project.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.sql.*;
import java.util.Iterator;
import java.util.Properties;

/**
 * Implement book repository but with a database backend
 */
//@ContextConfiguration("/Beans.xml")
public class BookRepoDatabase implements Repository<Book> {
    //@Autowired
    private DatabaseConnector databaseConnector;

    public BookRepoDatabase(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/Beans.xml");
        databaseConnector = (DatabaseConnector) context.getBean("databaseConnector");
    }

    @Override
    public Book get(int id) {
        DatabaseIterator<Book> bookIterator = new DatabaseIterator<Book>(databaseConnector,
                "SELECT * FROM books WHERE id =" + id,
                BookParser.INSTANCE);
        Book res = null;
        if (bookIterator.hasNext()){
            res = bookIterator.next();
        }
        bookIterator.close();
        return res;
    }

    @Override
    public Iterator<Book> getAll() {
        return new DatabaseIterator<Book>(databaseConnector,
                "SELECT * FROM books",
                BookParser.INSTANCE);
    }

    @Override
    public void add(Book book) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = databaseConnector.getConnection();

            pstmt = conn.prepareStatement("INSERT INTO books (id, isbn, title, author, publishDate) " +
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
            conn = databaseConnector.getConnection();

            pstmt = conn.prepareStatement("DELETE FROM books WHERE id = " + id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
        }
    }
}
