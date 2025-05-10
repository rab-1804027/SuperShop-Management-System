package Controller;

import Dto.ProductDto;
import Dto.SaleDto;
import Model.Product;
import Service.ProductService;
import Service.SaleService;
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
import java.util.List;
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
                break;
            }
            case "updateForm"-> {
                int id = Integer.parseInt(req.getParameter("id"));
                try {
                    ProductDto product = productService.findById(id);
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
                break;
            }
            case "saleRecords"->{
                try{
                    SaleService saleService = SaleService.getSingleObject();
                    int userId = (int) session.getAttribute("userId");
                    List<SaleDto> saleRecords = saleService.findAllByUserId(userId);

                    session.setAttribute("saleRecords", saleRecords);
                    session.setAttribute("action", action);
                    resp.sendRedirect("/jspPage/SaleRecords");
                }catch (Exception e){
                    logger.error("An error occurred while fetching sale records: {}", e.getMessage());
                }
                break;
            }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        switch (action){
            case "add": {
                try{
                    int userId = Integer.parseInt(req.getParameter("userId"));
                    String name = req.getParameter("name");
                    double price = Double.parseDouble(req.getParameter("price"));
                    double stockQuantity = Double.parseDouble(req.getParameter("stockQuantity"));

                    ProductValidator validator = ProductValidator.getSingleObject();
                    Map<String,String> errors = validator.validate(name, price, stockQuantity);

                    if(!errors.isEmpty()) {
                        logger.error(errors.toString());
                        session.setAttribute("errors", errors);
                        session.setAttribute("action", "productForm");
                        resp.sendRedirect("/jspPage/ProductForm");
                    }
                    else{
                        productService.save(new Product(userId, name, price, stockQuantity));
                        logger.info("Product::{} added successfully", name);
                        resp.sendRedirect("/dashboard");
                    }
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
                    ProductDto product = new ProductDto(id, name, price, stockQuantity);
                    productService.updateById(product);

                    session.removeAttribute("product");
                    resp.sendRedirect("/dashboard");
                } catch (Exception e){
                    logger.error("An error occurred while updating product: {}",e.getMessage());
                }
                break;
            }
        }
    }
}
