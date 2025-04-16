package Controller;

import Service.UserService;
import Utility.Constants;
import Validation.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import Exception.UserValidationException;
import Exception.UserNotFoundException;


@WebServlet("/register")
public class RegisterController extends HttpServlet {

    UserService userService = UserService.getSingleObject();
    UserValidator userValidation = UserValidator.getSingleObject();

    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("RegisterForm.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Registration form");
        String name = req.getParameter(Constants.UserInfo.NAME);
        String email = req.getParameter(Constants.UserInfo.EMAIL);
        String username = req.getParameter(Constants.UserInfo.USERNAME);
        String password = req.getParameter(Constants.UserInfo.PASSWORD);
        String confirmPassword = req.getParameter(Constants.UserInfo.CONFIRM_PASSWORD);

        try{
            UserValidator validator = UserValidator.getSingleObject();
            String error = validator.validateRegistration(req);

            if(error != null) {
                logger.error(error);
                req.setAttribute("error", error);
                req.getRequestDispatcher("RegisterForm.jsp").forward(req, resp);
            }

            userService.save(name, email, username, password);
            logger.info("User registered successfully");
            resp.sendRedirect("/login");
        } catch (UserValidationException e) {
            logger.error(username+ "got an error "+ e.getMessage());
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("RegisterForm.jsp").forward(req, resp);
        } catch (Exception e){
            logger.error("Error while saving user", e);
        }

    }
}
