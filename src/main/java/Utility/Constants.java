package Utility;

public class Constants {

    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static class ErrorMessage{
        public static final String USER_EXISTS = "Username is already in use";
        public static final String EMAIL_EXISTS = "Email is already in use";
        public static final String PASSWORD_NOT_MATCH = "Password must be the same";
        public static final String PASSWORD_EMPTY = "Password cannot be empty";
        public static final String NAME_EMPTY = "Name cannot be empty";
        public static final String USERNAME_EMPTY = "Username cannot be empty";
        public static final String INVALID_EMAIL = "The email address is invalid";
        public static final String LOGIN = "Username or Password is invalid";
    }
}
