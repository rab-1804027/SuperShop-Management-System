package Model;

public class CartItem {
    private Product product;
    private double quantity;
    private double price;

    public CartItem(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
        this.price = product.getPrice() * quantity;
    }

    public double getPrice() {
        return price;
    }
}
