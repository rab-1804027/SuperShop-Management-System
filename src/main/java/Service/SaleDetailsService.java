package Service;

import Dao.SaleDetailsDao;
import Model.CartItem;
import Model.ProductCart;

import java.sql.SQLException;

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
            double quantity = cartItem.getQuantity();
            double price = cartItem.getPrice();
            saleDetailsDao.save(saleId, productId, quantity, price);
        }
    }
}
