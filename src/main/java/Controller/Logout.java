package Controller;

import Utility.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(Logout.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info(request.getSession().getAttribute(Constants.UserInfo.USERNAME)+" Logged out");
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("/login");
    }
}
