package Mapper;

import Model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {
    private ProductMapper(){}
    private static final ProductMapper singleObject = new ProductMapper();
    public static ProductMapper getSingleObject(){
        return singleObject;
    }

    public Product toEntity(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        double price = result.getDouble("price");
        double stockQuantity = result.getDouble("stockQuantity");
        return new Product(id, name, price, stockQuantity);
    }
}
