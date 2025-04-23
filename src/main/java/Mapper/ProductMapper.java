package Mapper;

import Model.Product;

public class ProductMapper {
    private ProductMapper(){}
    private static final ProductMapper singleObject = new ProductMapper();
    public static ProductMapper getSingleObject(){
        return singleObject;
    }

    public Product toEntity(String name, Double price, Double stockQuantity) {
        return new Product(name, price, stockQuantity);
    }
}
