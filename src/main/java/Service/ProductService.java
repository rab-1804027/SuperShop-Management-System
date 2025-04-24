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

    public void updateById(Product product) throws SQLException {
        productDao.updateById(product);
    }

    public void deleteById(Integer id) throws SQLException {
        productDao.deleteById(id);
    }

    public Product findById(Integer id) throws SQLException {
        return productDao.findById(id);
    }

    public List<Product> findAll() throws SQLException {
        return productDao.findAll();
    }
}
