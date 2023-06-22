package com.example.mlt;
//import all the libraries needed
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    //Create a common database connection for using in all process
    public static Connection connection;

    //A method to get connection when the program need to connect to the mysql database
    public static Connection getConnections(){
        try {
            //Get connection from mysql database
            connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fopgui?allowPublicKeyRetrieval=true&autoReconnect=true&useUnicode=true&useSSL=false","root","ABc021231%ABc021231%");
            System.out.println("Database Connected");
        } catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    //A method to check connection whether it is successful or not
    public boolean checkConnections(){
        return connection != null;
    }

    //A method to close the database connection when not using it
    public static void closeConnections(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
