package Controller;

import Model.User;
import Service.UserService;
import Exception.UserNotFoundException;
import Exception.UserValidationException;
import Utility.Constants;
import Validation.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(LoginController.class);
    UserService userService = UserService.getSingleObject();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login form fetched");
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login attempted");
        String username = request.getParameter(Constants.UserInfo.USERNAME);
        String password = request.getParameter(Constants.UserInfo.PASSWORD);
        HttpSession session = request.getSession();
        try{
            UserValidator validator = UserValidator.getSingleObject();
            User user = validator.validateLogin(username, password);
            if(user != null) {
                logger.info(username+" Logged in");
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", user.getRole());
                response.sendRedirect("/");
            }
        } catch (UserNotFoundException | UserValidationException e){
            logger.error(username + "got an error while login: {}", e.getMessage());
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error(username + " " + e.getMessage());
        }

    }
}
