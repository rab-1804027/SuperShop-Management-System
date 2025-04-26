package Controller;

import Model.CartItem;
import Model.Product;
import Model.ProductCart;
import Service.ProductService;
import Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/productCart")
public class ProductCartController extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(ProductCartController.class);
    ProductService productService = ProductService.getSingleObject();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action){
            case "add": {
                try{
                    int productId = Integer.parseInt(req.getParameter("productId"));
                    double quantity = Integer.parseInt(req.getParameter("quantity"));

                    HttpSession session = req.getSession();
                    ProductCart cart = (ProductCart) session.getAttribute("cart");

                    Product product = productService.findById(productId);
                    CartItem cartItem = new CartItem(product, quantity);

                    cart.add(cartItem);
                    session.setAttribute("cart", cart);

                    resp.sendRedirect("/dashboard");
                }catch (Exception e)
                {
                    logger.error("An error occurred while adding product to cart: {}", e.getMessage());
                }
                break;
            }
            case "remove": {
                int productId = Integer.parseInt(req.getParameter("productId")); System.out.println(productId);
                HttpSession session = req.getSession();
                ProductCart cart = (ProductCart) session.getAttribute("cart");

                cart.removeByProductId(productId);
                session.setAttribute("cart", cart);
                session.setAttribute("action", "productCart");
                resp.sendRedirect("/jspPage/ProductCart");
                break;
            }
        }
    }
}

