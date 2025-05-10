package Model;

import java.time.LocalDateTime;

public class Sale {
    private int id;
    private int userId;
    private double totalPrice;
    private LocalDateTime saleTime;

    public Sale(int userId, double totalPrice) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.saleTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(LocalDateTime saleTime) {
        this.saleTime = saleTime;
    }
}
