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
    private String user;
    private String password;

    public void setDburl(String dburl){
        this.dburl = dburl;
    }

    public void setUser(String user){ this.user = user; }

    public void setPassword(String password){
        this.password = password;
    }

    public Connection getConnection(){
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);

        try {
            return DriverManager.getConnection(dburl, connectionProps);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
