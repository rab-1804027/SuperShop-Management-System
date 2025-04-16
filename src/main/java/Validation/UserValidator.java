package Validation;

import Model.User;
import Service.UserService;
import Utility.Constants;
import jakarta.servlet.http.HttpServletRequest;
import Exception.UserValidationException;
import Exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.logging.Logger;

public class UserValidator {

    UserService userService = UserService.getSingleObject();
    private static final Logger logger = Logger.getLogger(UserValidator.class.getName());

    private UserValidator(){}
    private static final UserValidator singleObject = new UserValidator();

    public static UserValidator getSingleObject() {
        return singleObject;
    }

    public String validateRegistration(HttpServletRequest request) throws SQLException, ClassNotFoundException, UserValidationException, UserNotFoundException {
        logger.info("Validating user");
        if(request.getParameter(Constants.UserInfo.NAME) == null || request.getParameter(Constants.UserInfo.NAME).isEmpty()) {
            logger.warning(Constants.ErrorMessage.NAME_EMPTY);
            throw new UserValidationException(Constants.ErrorMessage.NAME_EMPTY);
        }
        if(request.getParameter(Constants.UserInfo.EMAIL) == null || request.getParameter(Constants.UserInfo.EMAIL).isEmpty()) {
            logger.warning(Constants.ErrorMessage.Email_EMPTY);
            throw new UserValidationException(Constants.ErrorMessage.Email_EMPTY);
        }
        if(request.getParameter(Constants.UserInfo.USERNAME) == null || request.getParameter(Constants.UserInfo.USERNAME).isEmpty()) {
            logger.warning(Constants.ErrorMessage.USERNAME_EMPTY);
            throw new UserValidationException(Constants.ErrorMessage.USERNAME_EMPTY);
        }
        if(request.getParameter(Constants.UserInfo.PASSWORD) == null || request.getParameter(Constants.UserInfo.PASSWORD).isEmpty()) {
            logger.warning(Constants.ErrorMessage.PASSWORD_EMPTY);
            throw new UserValidationException(Constants.ErrorMessage.PASSWORD_EMPTY);
        }
        if(request.getParameter(Constants.UserInfo.CONFIRM_PASSWORD) == null || request.getParameter(Constants.UserInfo.CONFIRM_PASSWORD).isEmpty()) {
            logger.warning(Constants.ErrorMessage.CONFIRM_PASSWORD_EMPTY);
            throw new UserValidationException(Constants.ErrorMessage.CONFIRM_PASSWORD_EMPTY);
        }

        if(!request.getParameter(Constants.UserInfo.PASSWORD).equals(request.getParameter(Constants.UserInfo.CONFIRM_PASSWORD))) {
            logger.warning(Constants.ErrorMessage.PASSWORD_NOT_MATCH);
            throw new UserValidationException(Constants.ErrorMessage.PASSWORD_NOT_MATCH);
        }

        try{
            if (userService.findByUsername(request.getParameter(Constants.UserInfo.USERNAME)) != null) {
                logger.warning(Constants.ErrorMessage.USER_EXISTS);
                throw new UserValidationException(Constants.ErrorMessage.USER_EXISTS);
            }
        } catch (UserNotFoundException e) {
            logger.info(Constants.ErrorMessage.USER_NOT_FOUND);
        }
        try{
            if (userService.findByEmail(request.getParameter(Constants.UserInfo.EMAIL)) != null) {
                logger.warning(Constants.ErrorMessage.EMAIL_EXISTS);
                throw new UserValidationException(Constants.ErrorMessage.EMAIL_EXISTS);
            }
        } catch (UserNotFoundException e) {
            logger.info(Constants.ErrorMessage.USER_NOT_FOUND);
        }
        return null;
    }

    public User validateLogin(String username, String password) throws SQLException, ClassNotFoundException, UserValidationException, UserNotFoundException {
        if(username == null || username.isEmpty()) {
            logger.warning(Constants.ErrorMessage.USERNAME_EMPTY);
            throw new UserValidationException(Constants.ErrorMessage.USERNAME_EMPTY);
        }
        if(password == null || password.isEmpty()) {
            logger.warning(Constants.ErrorMessage.PASSWORD_EMPTY);
            throw new UserValidationException(Constants.ErrorMessage.PASSWORD_EMPTY);
        }

        User user = userService.findByUsername(username);
        if(user == null) {
            logger.warning(Constants.ErrorMessage.USER_NOT_FOUND);
            throw new UserNotFoundException(Constants.ErrorMessage.USER_NOT_FOUND);
        }
        else if(!user.getPassword().equals(password)) {
            logger.warning(username + " put an incorrect password");
            throw new UserValidationException(Constants.ErrorMessage.PASSWORD_INCORRECT);
        }
        else
            return user;
    }
}
