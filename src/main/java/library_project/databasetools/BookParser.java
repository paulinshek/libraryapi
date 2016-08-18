package library_project.databasetools;

import library_project.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pshek on 16/08/2016.
 */
public class BookParser implements RowParser<Book> {
    public static final BookParser INSTANCE = new BookParser();

    private BookParser(){}

    @Override
    public Book parse(ResultSet rs) throws SQLException {
            return new Book(rs.getInt("id"),
                    rs.getString("isbn"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publishDate"));
    }
}
