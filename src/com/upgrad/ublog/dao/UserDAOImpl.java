package com.upgrad.ublog.dao;

import com.upgrad.ublog.db.Database;
import com.upgrad.ublog.dtos.User;

import java.sql.*;

/**
 * TODO: 3.5. Implement the UserDAO interface and implement this class using the Singleton pattern.
 *  (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 *  UserDAOImpl and a public static getInstance() method which returns the instance attribute.)
 * TODO: 3.6. findByEmailId() method should take email id as an input parameter and
 *  return the user details corresponding to the email id from the USER table defined
 *  in the database. (Hint: You should get the connection using the Database class)
 * TODO: 3.7. create() method should take user details as input and insert these details
 *  into the USER table. Return the same User object which was passed as an input
 *  argument. (Hint: You should get the connection using the Database class)
 */

public class UserDAOImpl implements UserDAO{

    private static UserDAOImpl instance;
    private UserDAOImpl(){

    }
    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    public User findByEmailId(String emailId) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement=connection.createStatement();
        String sql = "select * from user where = "+ emailId;
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){
            User  user = new User();
            user.setUserId(resultSet.getInt("userId"));
            user.setEmailId(resultSet.getString("emailId"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }else {
            return null;
        }

    }
    public User create(User user) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "INSERT INTO user (userId,eamilId,password) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,user.getUserId());
        preparedStatement.setString(2,user.getEmailId());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.executeUpdate();

        return user;
    }



    public static void main(String[] args) {
        try {

            UserDAOImpl userDAO =UserDAOImpl.getInstance();
            User temp = new User();
            temp.setUserId(1);
            temp.setEmailId("temp@temp.temp");
            temp.setPassword("temp");
            System.out.println(userDAO.create(temp)) ;

            System.out.println(userDAO.findByEmailId("temp@temp.temp"));
        } catch (Exception e) {
            System.out.println("FAILED");
        }
//
//        /**
//         * Following should be the desired output of the main method.
//         * User{userId=11, emailId='temp@temp.temp', password='temp'}
//         */
    }
}
