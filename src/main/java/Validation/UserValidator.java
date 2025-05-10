package Validation;

import Dto.UserDto;
import Service.UserService;
import Utility.Constants;
import Utility.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import Exception.UserValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserValidator {

    UserService userService = UserService.getSingleObject();
    private final Logger logger = LoggerFactory.getLogger(UserValidator.class);

    private UserValidator(){}
    private static final UserValidator singleObject = new UserValidator();

    public static UserValidator getSingleObject() {
        return singleObject;
    }

    public Map<String,String> validateRegistration(HttpServletRequest request) throws SQLException {

        logger.info("Validating user registration for username: {}", request.getParameter("username"));
        Map<String, String> errors = new HashMap<>();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if(StringUtils.isNullorEmpty(name)) {
            errors.put("nameError",Constants.ErrorMessage.NAME_EMPTY);
        }
        if(!email.matches(Constants.EMAIL_REGEX)) {
            errors.put("email",Constants.ErrorMessage.INVALID_EMAIL);
        }
        if(StringUtils.isNullorEmpty(username)) {
            errors.put("usernameError",Constants.ErrorMessage.USERNAME_EMPTY);
        }
        if(StringUtils.isNullorEmpty(password)) {
            errors.put("passwordError",Constants.ErrorMessage.PASSWORD_EMPTY);
        }

        if(!password.equals(confirmPassword)) {
            errors.put("passwordMismatchError",Constants.ErrorMessage.PASSWORD_NOT_MATCH);
        }

        UserDto userDto = userService.findByUsername(username);
        if (userDto!=null) {
            errors.put("duplicateUsernameError",Constants.ErrorMessage.USER_EXISTS);
        }
        userDto = userService.findByEmail(email);
        if (userDto!=null) {
            errors.put("email",Constants.ErrorMessage.EMAIL_EXISTS);
        }
        return errors;
    }

    public Map<String,String> validateLogin(String username, String password) throws SQLException, UserValidationException {

        Map<String,String> errors = new HashMap<>();

        if(StringUtils.isNullorEmpty(username)) {
            errors.put("usernameError",Constants.ErrorMessage.USERNAME_EMPTY);
        }
        if(StringUtils.isNullorEmpty(password)) {
            errors.put("passwordError",Constants.ErrorMessage.PASSWORD_EMPTY);
        }
        return errors;
    }
}
