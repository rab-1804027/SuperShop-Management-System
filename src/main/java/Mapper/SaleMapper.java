package Mapper;

import Dto.SaleDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class SaleMapper {
    private SaleMapper(){}
    private static final SaleMapper singleObject = new SaleMapper();
    public static SaleMapper getSingleObject(){
        return singleObject;
    }

    public SaleDto toDto(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        double totalPrice = result.getDouble("totalPrice");
        LocalDateTime saleTime = result.getTimestamp("saleTime").toLocalDateTime();
        return new SaleDto(id, totalPrice, saleTime);
    }
}
