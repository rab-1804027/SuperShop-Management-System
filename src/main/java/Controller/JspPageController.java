package Controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

@WebServlet("/jspPage/*")
public class JspPageController extends HttpServlet {
    public void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException {
        HttpSession session = request.getSession();
        String action = session.getAttribute("action").toString();

        switch (action){
            case "productForm","updateForm" -> {
                Map<String,String> errors = (Map<String, String>) session.getAttribute("errors");
                session.removeAttribute("errors");

                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/ProductForm.jsp").forward(request, response);
                break;
            }
            case "productCart"-> {
                request.getRequestDispatcher("/ProductCart.jsp").forward(request, response);
                break;
            }
            case "saleRecords","checkout"->{
                request.getRequestDispatcher("/SaleRecords.jsp").forward(request, response);
                break;
            }
        }
    }
}
