package Model;

import java.time.LocalDateTime;

public class Sales {
    private int id;
    private int userId;
    private double price;
    private LocalDateTime salesTime;

    public Sales(int id, int userId, double price, LocalDateTime salesTime) {
        this.id = id;
        this.userId = userId;
        this.price = price;
        this.salesTime = salesTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getSalesTime() {
        return salesTime;
    }

    public void setSalesTime(LocalDateTime salesTime) {
        this.salesTime = salesTime;
    }
}
