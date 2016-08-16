package library_project.databasetools;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pshek on 16/08/2016.
 */
public interface RowParser<E> {
    E parse(ResultSet rs) throws SQLException;
}
