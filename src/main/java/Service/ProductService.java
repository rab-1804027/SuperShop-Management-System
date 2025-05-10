package Service;

import Dao.ProductDao;
import Dto.ProductDto;
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

    public void updateById(ProductDto product) throws SQLException {
        productDao.updateById(product);
    }

    public ProductDto findById(int id) throws SQLException {
        return productDao.findById(id);
    }

    public List<ProductDto> findAll(int userId) throws SQLException {
        return productDao.findAll(userId);
    }
}
