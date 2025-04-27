package Dao;

import Config.DbConfig;
import Dto.SaleDetailsDto;
import Mapper.SaleDetailsMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDetailsDao {
    Connection connection = DbConfig.connectDb();
    SaleDetailsMapper saleDetailsMapper = SaleDetailsMapper.getSingleObject();

    private SaleDetailsDao(){}
    private static final SaleDetailsDao singleObject = new SaleDetailsDao();
    public static SaleDetailsDao getSingleObject(){
        return singleObject;
    }

    public void save(int saleId, int productId, double quantity, double price)throws SQLException {
        String sqlQuery = "insert into saleDetails (saleId, productId, quantity, price) values (?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setInt(1, saleId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setDouble(3, quantity);
            preparedStatement.setDouble(4, price);
            preparedStatement.executeUpdate();

        }
    }

    public List<SaleDetailsDto> findDetailsForInvoice(int saleId)throws SQLException {
        String sqlQuery = "select * from saleDetails left join products on saleDetails.productId = products.id where saleId=?";
        List<SaleDetailsDto> saleDetails = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setInt(1, saleId);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                saleDetails.add(saleDetailsMapper.toDto(result));
            }
        }
        return saleDetails;
    }
}
