package Controller;

import Model.Product;
import Service.ProductService;
import Validation.ProductValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        switch (action){
            case "productForm"-> {
                session.setAttribute("action", action);
                resp.sendRedirect("/jspPage/ProductForm");
                //req.getRequestDispatcher("ProductForm.jsp").forward(req, resp);
                break;
            }
            case "updateForm"-> {
                int id = Integer.parseInt(req.getParameter("id"));
                try {
                    Product product = productService.findById(id);
                    session.setAttribute("product", product);
                    session.setAttribute("action", action);
                    resp.sendRedirect("/jspPage/UpdateProduct");
                } catch (Exception e) {
                    logger.error("An error occurred while fetching product by id: {}", e.getMessage());
                }
                break;
            }
            case "productCart"-> {

                session.setAttribute("action", action);
                resp.sendRedirect("/jspPage/ProductCart");
                //req.getRequestDispatcher("ProductCart.jsp").forward(req, resp);
                break;
            }
            case "delete"-> {
                doPost(req, resp);
                break;
            }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action){
            case "add": {
                try{
                    String name = req.getParameter("name");
                    double price = Double.parseDouble(req.getParameter("price"));
                    double stockQuantity = Double.parseDouble(req.getParameter("stockQuantity"));

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
                break;
            }
            case "update": {
                try{
                    int id = Integer.parseInt(req.getParameter("id"));
                    String name = req.getParameter("name");
                    double price = Double.parseDouble(req.getParameter("price"));
                    double stockQuantity = Double.parseDouble(req.getParameter("stockQuantity"));
                    Product product = new Product(id, name, price, stockQuantity);
                    productService.updateById(product);
                    HttpSession session = req.getSession();
                    session.removeAttribute("product");
                    resp.sendRedirect("/dashboard");
                } catch (Exception e){
                    logger.error("An error occurred while updating product: {}",e.getMessage());
                }
                break;
            }
            case "delete": {
                try{
                    int id = Integer.parseInt(req.getParameter("id"));
                    productService.deleteById(id);
                    resp.sendRedirect("/dashboard");
                } catch (Exception e){
                    logger.error("An error occurred while deleting product by id: {}", e.getMessage());
                }
                break;
            }
        }
    }
}
