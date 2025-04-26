package Controller;


import Dao.UserDao;
import Dto.UserInfoDto;
import Enums.ApprovalStatus;
import Model.CartItem;
import Model.Product;
import Model.ProductCart;
import Service.ProductService;
import Service.UserService;
import com.bappi.supershopmanagementsystem.HelloServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardController extends HelloServlet {

    UserService userService = UserService.getSingleObject();
    ProductService productService = ProductService.getSingleObject();
    UserDao userDao = UserDao.getSingleObject();
    private final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String role = (String) request.getSession().getAttribute("role");
        switch (role) {
            case "Admin" -> {
                try {
                    List<UserInfoDto> approvalShopkeeperList = userService.findByApprovalStatus(ApprovalStatus.APPROVED);
                    List<UserInfoDto> pendingShopkeeperList = userService.findByApprovalStatus(ApprovalStatus.PENDING);
                    HttpSession session = request.getSession();
                    session.setAttribute("approvalShopkeeperList", approvalShopkeeperList);
                    session.setAttribute("pendingShopkeeperList", pendingShopkeeperList);
                    request.getRequestDispatcher("AdminDashboard.jsp").forward(request,response);
                } catch (Exception e) {
                    logger.error("An error occurred while fetching user list by approvalStatus: ", e);
                }
            }
            case "Shopkeeper" -> {
                try {
                    HttpSession session = request.getSession();

                    List<Product> products = productService.findAll();
                    ProductCart productCart = (ProductCart) session.getAttribute("cart");

                    session.setAttribute("products", products);
                    if(productCart == null) {
                        productCart = ProductCart.getSingleObject();
                        productCart.clearCart();
                        session.setAttribute("cart", productCart);
                    }

                    request.getRequestDispatcher("ShopkeeperDashboard.jsp").forward(request, response);
                    //response.sendRedirect("ShopkeeperDashboard.jsp");
                } catch (Exception e) {
                    logger.error("An error occurred while forwarding to Shopkeeper.jsp: ", e);
                }
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();

//        switch (action){
//            case "ApproveShopkeeper":{
//                try{
//
//                }
//            }
//        }
    }
}
