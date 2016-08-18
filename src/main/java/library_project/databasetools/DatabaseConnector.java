package library_project.databasetools;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 */
public class DatabaseConnector {
    private String dburl;
    private Properties connectionProps;

    public DatabaseConnector(String dburl, String user, String password){
        this.dburl = dburl;
        connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);
    }

    public Connection getConnection(){


        try {
            return DriverManager.getConnection(dburl, connectionProps);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
