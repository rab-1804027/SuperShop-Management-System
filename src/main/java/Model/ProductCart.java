package Model;

import Dto.ProductDto;
import Service.ProductService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductCart {

    public static final ProductCart singleObject = new ProductCart();
    public static ProductCart getSingleObject(){
        return singleObject;
    }

    ProductService productService = ProductService.getSingleObject();
    private final List<CartItem> cartItems;
    private double totalPrice;

    private ProductCart(){
        cartItems = new ArrayList<>();
        totalPrice = 0;
    }

    public void add(CartItem cartItem) throws SQLException {
        for(CartItem item : cartItems){
            if(Objects.equals(item.getProduct().getId(), cartItem.getProduct().getId()))
            {
                totalPrice = totalPrice - item.getPrice();
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                totalPrice = totalPrice + item.getPrice();

                ProductDto productDto = item.getProduct();
                productDto.setStockQuantity(productDto.getStockQuantity() - cartItem.getQuantity());
                productService.updateById(productDto);
                return;
            }
        }

        cartItems.add(cartItem);
        totalPrice = totalPrice + cartItem.getPrice();

        ProductDto productDto = cartItem.getProduct();
        productDto.setStockQuantity(productDto.getStockQuantity() - cartItem.getQuantity());
        productService.updateById(productDto);
    }

    public void removeByProductId(int productId)throws SQLException{

        for(CartItem item : cartItems){
            if(Objects.equals(item.getProduct().getId(), productId))
            {
                cartItems.remove(item);
                totalPrice = totalPrice - item.getPrice();

                ProductDto productDto = item.getProduct();
                productDto.setStockQuantity(productDto.getStockQuantity() + item.getQuantity());
                productService.updateById(productDto);
                return;
            }
        }
    }

    public void clearCart(){
        cartItems.clear();
        totalPrice = 0;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
