package Model;

public class SaleDetails {
    private int id;
    private int saleId;
    private int productId;
    private double unitPrice;
    private double quantity;
    private double price;

    public SaleDetails(int id, int saleId, int productId,double unitPrice, double quantity, double price) {
        this.saleId = saleId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setUnitPrice(double unitPrice){
        this.unitPrice = unitPrice;
    }

    public double getUnitPrice(){
        return unitPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
