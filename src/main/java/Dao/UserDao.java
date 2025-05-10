package Dao;

import Config.DbConfig;
import Dto.UserDto;
import Dto.UserInfoDto;
import Enums.ApprovalStatus;
import Mapper.UserMapper;
import Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection connection = DbConfig.connectDb();

    UserMapper userMapper = UserMapper.getSingleObject();

    private UserDao() {}
    private static final UserDao singleObject = new UserDao();

    public static UserDao getSingleObject() {
        return singleObject;
    }

    public void save(User user) throws SQLException {

        String sqlQuery = "insert into users (name, username, email, password, role, registrationTime) values (?,?,?,?,?,?)";

        try(PreparedStatement pst = connection.prepareStatement(sqlQuery)) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getUsername());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPassword());
            pst.setString(5, user.getRole());
            pst.setTimestamp(6, Timestamp.valueOf(user.getRegistrationTime()));
            pst.executeUpdate();
        }
    }

    public void assignRole(String username, String role, ApprovalStatus status) throws SQLException {
        String sqlQuery = "update users set role=?, approvalStatus=? where username=?";
        try(PreparedStatement pst = connection.prepareStatement(sqlQuery)){
            pst.setString(1, role);
            pst.setString(2, status.name());
            pst.setString(3, username);
            pst.executeUpdate();
        }
    }

    public UserDto findByUsername(String username) throws SQLException {
        String sqlQuery = "select * from users where username=?";
        try(PreparedStatement pst = connection.prepareStatement(sqlQuery)){
            pst.setString(1, username);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return userMapper.resultToDto(result);
            }
        }
        return null;
    }

    public UserDto findByEmail(String email) throws SQLException {
        String sqlQuery = "select * from users where email=?";
        try(PreparedStatement pst = connection.prepareStatement(sqlQuery)){
            pst.setString(1, email);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return userMapper.resultToDto(result);
            }
        }
        return null;
    }

    public List<UserInfoDto> findByApprovalStatus(ApprovalStatus status) throws SQLException {
        List<UserInfoDto> userList = new ArrayList<>();
        String sqlQuery = "select * from users where approvalStatus=?";
        try(PreparedStatement pst = connection.prepareStatement(sqlQuery)){
            pst.setString(1, status.name());
            ResultSet result = pst.executeQuery();
            while(result.next()){
                userList.add(userMapper.resultToInfoDto(result));
            }
        }
        return userList;
    }

}
