package Controller;

import Dto.UserDto;
import Service.UserService;
import Utility.Constants;
import Utility.PasswordHashing;
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
import java.util.Map;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getSession().getAttribute("username")!=null) {
            response.sendRedirect("/dashboard");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        logger.info("{} attempted for login",username);
        HttpSession session = request.getSession();
        try{
            UserValidator validator = UserValidator.getSingleObject();
            Map<String,String> errors = validator.validateLogin(username, password);
            if(!errors.isEmpty()) {
                logger.error(errors.toString());
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

            UserService userService = UserService.getSingleObject();
            UserDto userDto = userService.findByUsername(username);

            if(userDto!=null && PasswordHashing.checkPassword(password, userDto.getPassword())) {
                logger.info("{} logged in successfully", username);
                session.setAttribute("userId", userDto.getId());
                session.setAttribute("username", username);
                session.setAttribute("role", userDto.getRole());
                response.sendRedirect("/dashboard");
            } else {
                request.setAttribute("error", Constants.ErrorMessage.LOGIN);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (Exception e){
            logger.error("An exception occurred while {} is trying to login: {}", username, e.getMessage());
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }

    }
}
