package Mapper;

import Dto.UserDto;
import Dto.UserInfoDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UserMapper {
    private static final UserMapper singleObject = new UserMapper();
    private UserMapper(){}

    public static UserMapper getSingleObject(){
        return singleObject;
    }

    public UserDto resultToDto(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String username = result.getString("username");
        String password = result.getString("password");
        String role = result.getString("role");
        return new UserDto(id, username, password, role);
    }

    public UserInfoDto resultToInfoDto(ResultSet result) throws SQLException{
        String name = result.getString("name");
        String username = result.getString("username");
        String email = result.getString("email");
        LocalDateTime registrationTime = result.getTimestamp("registrationTime").toLocalDateTime();

        return new UserInfoDto(name, username, email, registrationTime);
    }
}
