package Dao;

import Config.DbConfig;
import Model.User;
import Utility.Constants;

import java.io.IOException;
import java.sql.*;

public class UserDao {
    Connection connection = DbConfig.connectDb();

    private UserDao() {}
    private static final UserDao singleObject = new UserDao();

    public static UserDao getSingleObject() {
        return singleObject;
    }

//    public boolean usernameValidation(String username) throws SQLException, ClassNotFoundException {
//        String sqlQuery = "select * from users where username=?";
//        try(PreparedStatement pst = connection.prepareStatement(sqlQuery)){
//            pst.setString(1, username);
//            ResultSet result = pst.executeQuery();
//            return result.next();
//        }
//    }
//
//    public boolean emailValidation(String email) throws SQLException, ClassNotFoundException {
//        String sqlQuery = "select * from users where email=?";
//        try(PreparedStatement pst = connection.prepareStatement(sqlQuery)){
//            pst.setString(1, email);
//            ResultSet result = pst.executeQuery();
//            return result.next();
//        }
//    }

    public void save(User user) throws SQLException, ClassNotFoundException,IOException {

        String sqlQuery = "insert into users (name, username, email, password, role, registrationTime) values (?,?,?,?,?,?)";

        try(PreparedStatement pst = connection.prepareStatement(sqlQuery)) {
            System.out.println(user);
            pst.setString(1, user.getName());
            pst.setString(2, user.getUsername());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPassword());
            pst.setString(5, user.getRole());
            pst.setTimestamp(6, Timestamp.valueOf(user.getRegistrationTime()));
            pst.executeUpdate();
        }
    }

    public User findByUsername(String username) throws SQLException, ClassNotFoundException {
        String sqlQuery = "select * from users where username=?";
        try(PreparedStatement pst = connection.prepareStatement(sqlQuery)){
            pst.setString(1, username);
            ResultSet result = pst.executeQuery();
            User user = null;
            while(result.next()) {
                int id = result.getInt(Constants.UserInfo.ID);
                String name = result.getString(Constants.UserInfo.NAME);
                String email = result.getString(Constants.UserInfo.EMAIL);
                String password = result.getString(Constants.UserInfo.PASSWORD);
                String role = result.getString(Constants.UserInfo.ROLE);
                user = new User(id, name, email, username, password, role);
            }
            return user;
        }
    }
    public User findByEmail(String email) throws SQLException, ClassNotFoundException {
        String sqlQuery = "select * from users where email=?";
        try(PreparedStatement pst = connection.prepareStatement(sqlQuery)){
            pst.setString(1, email);
            ResultSet result = pst.executeQuery();
            User user = null;
            while(result.next()) {
                int id = result.getInt(Constants.UserInfo.ID);
                String name = result.getString(Constants.UserInfo.NAME);
                String username = result.getString(Constants.UserInfo.USERNAME);
                String password = result.getString(Constants.UserInfo.PASSWORD);
                String role = result.getString(Constants.UserInfo.ROLE);
                user = new User(id, name, email, username, password, role);
            }
            return user;
        }
    }
}
