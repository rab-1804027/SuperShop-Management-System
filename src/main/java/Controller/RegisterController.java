package Controller;

import Service.UserService;
import Validation.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;


@WebServlet("/register")
public class RegisterController extends HttpServlet {

    UserService userService = UserService.getSingleObject();

    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("RegisterForm.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        logger.info("Username::{} is trying to register",username);
        try{
            UserValidator validator = UserValidator.getSingleObject();
            Map<String,String> errors = validator.validateRegistration(req);

            if(!errors.isEmpty()) {
                logger.error(errors.toString());
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("RegisterForm.jsp").forward(req, resp);
            }

            userService.save(name, email, username, password);
            logger.info("Username::{} registered successfully", username);
            resp.sendRedirect("/login");
        } catch (Exception e){
            logger.error("Error while saving {}:: {}",username, e.getMessage());
        }

    }
}
