package Service;

import Dao.ProductDao;
import Model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    ProductDao productDao = ProductDao.getSingleObject();

    private static final ProductService singleObject = new ProductService();
    private ProductService(){}

    public static ProductService getSingleObject(){
        return singleObject;
    }

    public void save(Product product) throws SQLException {
        productDao.save(product);
    }

    public List<Product> findAll() throws SQLException {
        return productDao.findAll();
    }
}
