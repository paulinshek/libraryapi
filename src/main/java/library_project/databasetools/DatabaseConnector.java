package library_project.databasetools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by pshek on 17/08/2016.
 */
public class DatabaseConnector {
    String dburl;
    Properties connectionProps;

    public void setDburl(String dbUrl){

    }

    public void setUsername(String username){

    }

    public void setPassword(String username){
        
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(dburl, connectionProps);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
