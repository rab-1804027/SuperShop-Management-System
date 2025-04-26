package Mapper;

import Dto.ProductDto;
import Model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {
    private ProductMapper(){}
    private static final ProductMapper singleObject = new ProductMapper();
    public static ProductMapper getSingleObject(){
        return singleObject;
    }

    public ProductDto toDto(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        double price = result.getDouble("price");
        double stockQuantity = result.getDouble("stockQuantity");
        return new ProductDto(id, name, price, stockQuantity);
    }
}
