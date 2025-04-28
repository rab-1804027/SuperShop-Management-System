package Utility;

import Dto.SaleDetailsDto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.OutputStream;
import java.util.List;

public class InvoicePdfGenerator {
    public static void generateInvoicePdf(List<SaleDetailsDto> saleDetails, double totalPrice, String saleTime,
                                          OutputStream outputStream) throws DocumentException {
        // Create PDF document
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        try {
            // Add title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("INVOICE", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Add date and time
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);
            Paragraph dateTime = new Paragraph("Date/Time: " + saleTime, normalFont);
            dateTime.setAlignment(Element.ALIGN_RIGHT);
            document.add(dateTime);
            document.add(Chunk.NEWLINE);

            // Create table
            PdfPTable table = new PdfPTable(4); // 4 columns
            table.setWidthPercentage(100);

            // Add table headers
            String[] headers = {"Product Name", "Unit Price", "Quantity", "Price"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
            }

            // Add data rows
            for (SaleDetailsDto detail : saleDetails) {
                table.addCell(new Phrase(detail.getProductName(), normalFont));
                table.addCell(new Phrase(String.format("%.2f", detail.getUnitPrice()), normalFont));
                table.addCell(new Phrase(String.format("%.2f", detail.getQuantity()), normalFont));
                table.addCell(new Phrase(String.format("%.2f", detail.getPrice()), normalFont));
            }

            document.add(table);
            document.add(Chunk.NEWLINE);

            // Add total price
            Paragraph total = new Paragraph(
                    String.format("Total Price: %.2f", totalPrice),
                    new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)
            );
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

        } finally {
            document.close();
        }
    }

}
