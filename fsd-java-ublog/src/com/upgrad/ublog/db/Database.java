package com.upgrad.ublog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * TODO 3.2: Implement the Database class using the Singleton Pattern (Hint. Should have the
 *  private no-arg constructor, a private static connection attribute of type Connection and a public
 *  static getConnection() method which return the connection attribute).
 * TODO 3.3: The getInstance() method should check if the connection attribute is null. If yes, then
 *  it should create a connection object which is connected with the local database and assign this
 *  connection object to the connection attribute. In the end, return this connection attribute.
 * TODO 3.4: You should handle the ClassNotFoundException and SQLException individually,
 *  and not using the Exception class.
 */

public class Database {

    private Database(){

    }
    private static Connection connection;
    public static Connection getInstance(){
       if (connection==null){
           return getConnection();
       }else
           return connection;
    }
    public static Connection getConnection() {
        if(connection==null){
           String url = "jdbc:mysql://localhost:3306/ublog";
           String userName = "root";
           String password = "Sivani@27";
           try{

               Class.forName("com.mysql.cj.jdbc.Driver");
               connection = DriverManager.getConnection(url,userName,password);
           }catch(ClassNotFoundException e){
               System.out.println("MYSQL driver not found");
           }catch(SQLException e){
               e.printStackTrace();
           }
        }
    return connection;
    }
    public static void main(String[] args) throws SQLException {
        try {
            Database.getConnection();
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println("Not Connected");
        }
    }
}
