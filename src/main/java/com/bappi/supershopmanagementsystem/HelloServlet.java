package com.bappi.supershopmanagementsystem;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import Controller.DashboardController;
import Enums.ApprovalStatus;
import Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/")
public class HelloServlet extends HttpServlet {

    UserService userService = UserService.getSingleObject();
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();
        switch (action){
            case "/approveShopkeeper": {
                String username = request.getParameter("username");
                String role = "Shopkeeper";
                try {
                    userService.assignRole(username, role, ApprovalStatus.APPROVED);
                    response.sendRedirect("/dashboard");
                } catch (Exception e) {
                    logger.error("An Error occurred while assign role for user:{}, message {}", username, e);
                }
            }
            case "/rejectShopkeeper": {
                String username = request.getParameter("username");
                String role = "null";
                try {
                    userService.assignRole(username, role, ApprovalStatus.REJECTED);
                    response.sendRedirect("/dashboard");
                } catch (Exception e) {
                    logger.error("An Error occurred while assign role for user:{}, message {}", username, e);
                }
            }

        }
    }
}