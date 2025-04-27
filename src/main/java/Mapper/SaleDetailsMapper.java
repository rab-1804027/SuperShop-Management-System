package Mapper;

import Dao.SaleDetailsDao;
import Dto.SaleDetailsDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleDetailsMapper {
    private SaleDetailsMapper(){}
    private static final SaleDetailsMapper singleObject = new SaleDetailsMapper();
    public static SaleDetailsMapper getSingleObject(){
        return singleObject;
    }

    public SaleDetailsDto toDto(ResultSet result) throws SQLException {
        String productName = result.getString("name");
        double unitPrice = result.getDouble("saleDetails.unitPrice");
        int quantity = result.getInt("quantity");
        double totalPrice = result.getDouble("saleDetails.price");
        return new SaleDetailsDto(productName, unitPrice, quantity, totalPrice);
    }
}
