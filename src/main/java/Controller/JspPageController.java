package Controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;

@WebServlet("/jspPage/*")
public class JspPageController extends HttpServlet {
    public void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException {
        HttpSession session = request.getSession();
        String action = session.getAttribute("action").toString();

        switch (action){
            case "productForm","updateForm" -> {
                request.getRequestDispatcher("/ProductForm.jsp").forward(request, response);
                break;
            }
            case "productCart"-> {
                request.getRequestDispatcher("/ProductCart.jsp").forward(request, response);
                break;
            }
        }
    }
}
