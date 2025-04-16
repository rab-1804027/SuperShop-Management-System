package Service;

import Dao.UserDao;
import Model.User;
import Exception.UserNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserService {

    UserDao userDao = UserDao.getSingleObject();
    Logger logger = Logger.getLogger(UserService.class.getName());

    private UserService(){}
    private static final UserService singleObject = new UserService();

    public static UserService getSingleObject() {
        return singleObject;
    }

//    public boolean usernameValidation(String username) throws SQLException, ClassNotFoundException {
//        return userDao.usernameValidation(username);
//    }
//
//    public boolean emailValidation(String username) throws SQLException, ClassNotFoundException {
//        return userDao.emailValidation(username);
//    }

    public void save(String name, String email, String username, String password) throws SQLException, IOException, ClassNotFoundException {
        logger.info("Saving user");
        User user = new User(name, email, username, password);
        userDao.save(user);
    }

    public User findByUsername(String username) throws SQLException, ClassNotFoundException, UserNotFoundException {
        User user = userDao.findByUsername(username);
        return user;
    }

    public User findByEmail(String email) throws SQLException, ClassNotFoundException, UserNotFoundException {
        User user = userDao.findByEmail(email);
        return user;
    }
}
