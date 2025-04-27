package Controller;

import Dao.SaleDetailsDao;
import Dto.SaleDetailsDto;
import Dto.SaleDto;
import Service.SaleDetailsService;
import Service.SaleService;
import Utility.InvoicePdfGenerator;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

@WebServlet("/getInvoicePdf")
public class GetInvoicePdf extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(GetInvoicePdf.class);
    SaleDetailsService saleDetailsService = SaleDetailsService.getSingleObject();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException{
        try {
            HttpSession session = request.getSession();
            int saleId = (int) session.getAttribute("saleId");
            session.removeAttribute("saleId");
            saleDetailsService.findDetailsForInvoice(saleId, response);
        }catch (Exception e){
            logger.error("An error occurred while fetching invoice pdf information: {}", e.getMessage());
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException {
        try{
            int saleId = Integer.parseInt(request.getParameter("saleId")); System.out.println(saleId);
            saleDetailsService.findDetailsForInvoice(saleId,  response);

        }catch (Exception e){
            logger.error("An error occurred while fetching invoice pdf information: {}", e.getMessage());
        }
    }
}
