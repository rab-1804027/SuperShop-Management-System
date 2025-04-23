package Controller;

import Model.Product;
import Service.ProductService;
import Validation.ProductValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/product")
public class ProductController extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    ProductService productService = ProductService.getSingleObject();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "productForm": {
                resp.sendRedirect("ProductForm.jsp");
            }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        Double price = Double.valueOf(req.getParameter("price"));
        Double stockQuantity = Double.valueOf(req.getParameter("stockQuantity"));
        switch (action){
            case "addProduct": {
                try{
                    ProductValidator validator = ProductValidator.getSingleObject();
                    Map<String,String> errors = validator.validate(name, price, stockQuantity);
                    if(!errors.isEmpty()) {
                        logger.error(errors.toString());
                        req.setAttribute("errors", errors);
                        req.getRequestDispatcher("ProductForm.jsp").forward(req, resp);
                    }

                    productService.save(new Product(name, price, stockQuantity));
                    logger.info("Product::{} added successfully", name);
                    resp.sendRedirect("/dashboard");
                } catch (Exception e){
                    logger.error("An error occurred while saving product: {}", e.getMessage());
                    req.getRequestDispatcher("ProductForm.jsp").forward(req, resp);
                }
            }
            case "updateProduct": {

            }
        }
    }
}
