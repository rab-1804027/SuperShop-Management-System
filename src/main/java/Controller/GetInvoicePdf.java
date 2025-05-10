package Controller;

import Service.SaleDetailsService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/getInvoicePdf")
public class GetInvoicePdf extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(GetInvoicePdf.class);
    SaleDetailsService saleDetailsService = SaleDetailsService.getSingleObject();

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            HttpSession session = request.getSession();
            int saleId = (int) session.getAttribute("saleId");
            session.removeAttribute("saleId");
            saleDetailsService.findDetailsForInvoice(saleId, response);
        }catch (Exception e){
            logger.error("An error occurred while fetching invoice pdf information: {}", e.getMessage());
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try{
            int saleId = Integer.parseInt(request.getParameter("saleId")); System.out.println(saleId);
            saleDetailsService.findDetailsForInvoice(saleId,  response);

        }catch (Exception e){
            logger.error("An error occurred while fetching invoice pdf information: {}", e.getMessage());
        }
    }
}
