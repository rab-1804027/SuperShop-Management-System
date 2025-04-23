package Dao;

import Config.DbConfig;
import Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    Connection connection = DbConfig.connectDb();
    private static final ProductDao singleObject = new ProductDao();
    private ProductDao(){}

    public static ProductDao getSingleObject(){
        return singleObject;
    }

    public void save(Product product) throws SQLException {
        String sqlQuery = "insert into products (name, price, stockQuantity) values (?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDouble(3, product.getStockQuantity());
            preparedStatement.executeUpdate();
        }
    }

    public List<Product> findAll() throws SQLException {
        String sqlQuery = "select * from products";
        List<Product> products = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery))
        {
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                String name = result.getString("name");
                Double price = result.getDouble("price");
                Double stockQuantity = result.getDouble("stockQuantity");
                products.add(new Product(name, price, stockQuantity));
            }
            return products;
        }
    }
}
