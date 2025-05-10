package Service;

import Dao.UserDao;
import Dto.UserDto;
import Dto.UserInfoDto;
import Enums.ApprovalStatus;
import Model.User;
import Utility.PasswordHashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    UserDao userDao = UserDao.getSingleObject();
    Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserService(){}
    private static final UserService singleObject = new UserService();

    public static UserService getSingleObject() {
        return singleObject;
    }

    public void save(String name, String email, String username, String password) throws SQLException {
        logger.info("Saved a new user username::{}", username);
        password = PasswordHashing.hashPassword(password);
        User user = new User(name, email, username, password);
        userDao.save(user);
    }

    public void assignRole(String username, String role, ApprovalStatus status) throws SQLException {
        userDao.assignRole(username, role, status);
    }

    public UserDto findByUsername(String username) throws SQLException {
        return userDao.findByUsername(username);
    }

    public UserDto findByEmail(String email) throws SQLException {
        return userDao.findByEmail(email);
    }

    public List<UserInfoDto> findByApprovalStatus(ApprovalStatus status) throws SQLException {
        return userDao.findByApprovalStatus(status);
    }
}
