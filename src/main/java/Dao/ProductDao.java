package Dao;

import Config.DbConfig;
import Dto.ProductDto;
import Mapper.ProductMapper;
import Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    Connection connection = DbConfig.connectDb();
    ProductMapper productMapper = ProductMapper.getSingleObject();
    private static final ProductDao singleObject = new ProductDao();
    private ProductDao(){}

    public static ProductDao getSingleObject(){
        return singleObject;
    }

    public void save(Product product) throws SQLException {
        String sqlQuery = "insert into products (userId, name, price, stockQuantity) values (?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setInt(1, product.getUserId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setDouble(4, product.getStockQuantity());
            preparedStatement.executeUpdate();
        }
    }

    public void updateById(ProductDto product) throws SQLException {
        String sqlQuery = "update products set name=?, price=?, stockQuantity=? where id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDouble(3, product.getStockQuantity());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteById(Integer id) throws SQLException {
        String sqlQuery = "delete from products where id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public ProductDto findById(Integer id) throws SQLException {
        String sqlQuery = "select * from products where id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()){
                return productMapper.toDto(result);
            }
        }
        return null;
    }

    public List<ProductDto> findAll(int userId) throws SQLException {
        String sqlQuery = "select * from products where userId=?";
        List<ProductDto> products = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery))
        {
            preparedStatement.setInt(1, userId);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                products.add(productMapper.toDto(result));
            }
            return products;
        }
    }
}
