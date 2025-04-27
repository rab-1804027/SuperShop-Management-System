package Dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaleDto {
    private int id;
    private double totalPrice;
    private String saleTime;

    public SaleDto(int id, double totalPrice, LocalDateTime saleTime) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.saleTime = formateTime(saleTime);
    }

    private String formateTime(LocalDateTime time)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm:ss");
        return time.format(formatter);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(LocalDateTime saleTime) {
        this.saleTime = formateTime(saleTime);
    }
}
