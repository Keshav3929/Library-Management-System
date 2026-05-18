package src.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
    public static Connection getConnection() throws SQLException{
       String URL="jdbc:mysql://localhost:3306/Library_db";
            String USERNAME="root";
            String Password="root@123";
            return DriverManager.getConnection(URL, USERNAME, Password);
    }
}