package Controller;

import Dto.ProductDto;
import Dto.UserInfoDto;
import Enums.ApprovalStatus;
import Model.ProductCart;
import Service.ProductService;
import Service.UserService;
import com.bappi.supershopmanagementsystem.HelloServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@WebServlet("/dashboard")
public class DashboardController extends HelloServlet {

    UserService userService = UserService.getSingleObject();
    ProductService productService = ProductService.getSingleObject();
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
                    int userId = (int) session.getAttribute("userId");
                    List<ProductDto> products = productService.findAll(userId);
                    ProductCart productCart = (ProductCart) session.getAttribute("cart");

                    session.setAttribute("products", products);
                    if(productCart == null) {
                        productCart = ProductCart.getSingleObject();
                        productCart.clearCart();
                        session.setAttribute("cart", productCart);
                    }

                    String error = (String) session.getAttribute("error");
                    session.removeAttribute("error");

                    request.setAttribute("error", error);
                    request.getRequestDispatcher("ShopkeeperDashboard.jsp").forward(request, response);
                } catch (Exception e) {
                    logger.error("An error occurred while forwarding to Shopkeeper.jsp: ", e);
                }
            }
        }
    }
}
