package library_project.databasetools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by pshek on 17/08/2016.
 */
public class DatabaseConnector {
    String dburl;
    Properties connectionProps;

    public void setDburl(String dburl){
        this.dburl = dburl;
    }

    public void setUser(String user){
        connectionProps.put("user", user);
    }

    public void setPassword(String password){
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
