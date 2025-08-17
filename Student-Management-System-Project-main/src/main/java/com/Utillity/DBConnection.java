package com.Utillity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getDataConnection() {
        Connection con = null;
        
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/java_db", // your DB name
                "root",                                  // your MySQL username
                "Pawan@1234"                          // your MySQL password
            );
        }
        catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        } 
        catch (SQLException e) {
            System.err.println("Connection failed.");
            e.printStackTrace();
        }
        
        return con;
    }
}
