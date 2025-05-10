package Model;

import Dto.ProductDto;

public class CartItem {
    private ProductDto product;
    private double quantity;
    private double price;

    public CartItem(ProductDto product, double quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice() * quantity;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
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
