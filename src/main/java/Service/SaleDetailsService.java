package Service;

import Dao.SaleDetailsDao;
import Dto.SaleDetailsDto;
import Dto.SaleDto;
import Model.CartItem;
import Model.ProductCart;
import Utility.InvoicePdfGenerator;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SaleDetailsService {

    SaleDetailsDao saleDetailsDao = SaleDetailsDao.getSingleObject();

    private SaleDetailsService(){}
    private static final SaleDetailsService singleObject = new SaleDetailsService();
    public static SaleDetailsService getSingleObject(){
        return singleObject;
    }

    public void save(int saleId, ProductCart cart)throws SQLException {
        for(CartItem cartItem : cart.getCartItems()){
            int productId = cartItem.getProduct().getId();
            double unitPrice = cartItem.getProduct().getPrice();
            double quantity = cartItem.getQuantity();
            double price = cartItem.getPrice();
            saleDetailsDao.save(saleId, productId, unitPrice, quantity, price);
        }
    }

    public void findDetailsForInvoice(int saleId, HttpServletResponse response) throws SQLException, DocumentException, IOException, ServletException {
        try {
          //  int saleId = Integer.parseInt(request.getParameter("saleId")); System.out.println(saleId);
            SaleService saleService = SaleService.getSingleObject();
            SaleDto saleDto= saleService.findBySaleId(saleId);
            double totalPrice = saleDto.getTotalPrice();
            String saleTime = saleDto.getSaleTime();

            List<SaleDetailsDto> saleDetails = saleDetailsDao.findDetailsForInvoice(saleId);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=Invoice_" + saleId + ".pdf");

            InvoicePdfGenerator.generateInvoicePdf(saleDetails,
                    totalPrice,
                    saleTime,
                    response.getOutputStream()
            );
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid sale ID or total price", e);
        }
    }
}
