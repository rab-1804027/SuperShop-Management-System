package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductCart {

    public static final ProductCart singleObject = new ProductCart();
    public static ProductCart getSingleObject(){
        return singleObject;
    }

    private final List<CartItem> cartItems;
    private double totalPrice;

    private ProductCart(){
        cartItems = new ArrayList<>();
        totalPrice = 0;
    }

    public void add(CartItem cartItem){

        for(CartItem item : cartItems){
            if(Objects.equals(item.getProduct().getId(), cartItem.getProduct().getId()))
            {
                totalPrice = totalPrice - item.getPrice();
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                totalPrice = totalPrice + item.getPrice();
                return;
            }
        }

        cartItems.add(cartItem);
        totalPrice = totalPrice + cartItem.getPrice();
    }

    public void removeByProductId(int productId){

        for(CartItem item : cartItems){
            if(Objects.equals(item.getProduct().getId(), productId))
            {
                cartItems.remove(item);
                totalPrice = totalPrice - item.getPrice();
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
