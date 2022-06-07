package com.example.mlt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {


    public static Connection connection;

    public static Connection getConnections(){
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/fopgui?useSSL=false","root","ABC123");
            System.out.println("Database Connected");

       // } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
      //      e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }


        return connection;
    }

    public boolean checkConnections(){
        return connection != null;
    }

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
