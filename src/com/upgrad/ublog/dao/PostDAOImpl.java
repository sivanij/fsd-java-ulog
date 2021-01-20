package com.upgrad.ublog.dao;
import com.upgrad.ublog.dtos.Post;
/**
 * TODO: 3.19. Implement the PostsDAO interface and implement this class using the Singleton pattern.
 *  (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 *  PostDAOImpl and a public static getInstance() method which returns the instance attribute.)
 * TODO: 3.20. Define the following methods and return null for each of them. You will provide a
 *  proper implementation for each of these methods, later in this project.
 *  a. findByEmailId()
 *  b. findByTag()
 *  c. findAllTags()
 *  d. findByPostId()
 *  e. deleteByPostId() (return false for this method for now)
 * TODO: 3.21. create() method should take post details as input and insert these details into
 *  the POST table. Return the same Post object which was passed as an input argument.
 *  (Hint: You should get the connection using the DatabaseConnection class)
 */

/**
 * TODO: 4.1. Implement findByEmailId() method which takes email id as an input parameter and
 *  returns all the posts corresponding to the email id from the Post table defined
 *  in the database.
 */

/**
 * TODO: 4.4. Implement the deleteByPostId() method which takes post id as an input argument and delete
 *  the corresponding post from the database. If the post was deleted successfully, then return true,
 *  otherwise, return false. (Hint: The executeUpdate() method returns the count of rows affected by the
 *  query.)
 * TODO: 4.5. Implement the findByPostId() method which takes post id as an input argument and return the
 *  post details from the database. If no post exists for the given id, then return a Post object
 *  without setting any of its attributes.
 */

import com.upgrad.ublog.db.Database;

import java.sql.*;
import java.util.List;

/**
 * TODO: 4.8. Implement findAllTags() method which returns a list of all tags in the POST table.
 * TODO: 4.9. Implement findByTag() method which takes "tag" as an input argument and returns all the
 *  posts corresponding to the input "tag" from the POST table defined in the database.
 */

public class PostDAOImpl implements PostDAO{
    private static PostDAOImpl instance;
    private PostDAOImpl(){

    }
    public static PostDAOImpl getInstance() {
        if (instance == null) {
            instance = new PostDAOImpl();
        }
        return instance;
    }
    public Post create(Post post) throws SQLException{
        Connection connection = Database.getConnection();
        String sql = "INSERT INTO user (postId,eamilId,tag) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,post.getPostId());
        preparedStatement.setString(2,post.getEmailId());
        preparedStatement.setString(3,post.getTag());
        preparedStatement.executeUpdate();

        return post;

    }

    public List<Post> findByTag(String tag) throws SQLException{
        Connection connection = Database.getConnection();
        Statement statement=connection.createStatement();
        String sql = "select * from post where = "+ tag;
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){
            Post  post = new Post();
            post.setPostId(resultSet.getInt("postId"));
            post.setEmailId(resultSet.getString("emailId"));
            post.setTag(resultSet.getString("tag"));
            return (List<Post>) post;
        }else {
            return null;
        }
    }
    public Post findByPostId(int postId) throws SQLException{
        Connection connection = Database.getConnection();
        Statement statement=connection.createStatement();
        String sql = "select * from post where = "+ postId;
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){
            Post  post = new Post();
            post.setPostId(resultSet.getInt("postId"));
            post.setEmailId(resultSet.getString("emailId"));
            post.setTag(resultSet.getString("tag"));
            return  post;
        }else {
            return null;
        }
    }
    public List<String> findAllTags() throws SQLException{
        Connection connection = Database.getConnection();
        Statement statement=connection.createStatement();
        String sql = "select tag from post";
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){
            Post  post = new Post();
            post.setTag(resultSet.getString("tag"));
            return (List<String>) post;
        }else {
            return null;
        }
    }
    public boolean deleteByPostId(int postId) throws SQLException{
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            String sql = "DELETE *FROM post  WHERE ="+postId;
            statement.executeUpdate(sql);
            return true;


    }
    public List<Post> findByEmailId(String emailId) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement=connection.createStatement();
        String sql = "select * from post where = "+ emailId;
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){
            Post  post = new Post();
            post.setPostId(resultSet.getInt("postId"));
            post.setEmailId(resultSet.getString("emailId"));
            post.setTag(resultSet.getString("tag"));
            return (List<Post>) post;
        }else {
            return null;
        }

    }

}
