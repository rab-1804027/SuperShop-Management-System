package Dao;

import Config.DbConfig;
import Dto.SaleDto;
import Mapper.SaleMapper;
import Model.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDao {
    Connection connection = DbConfig.connectDb();
    SaleMapper saleMapper = SaleMapper.getSingleObject();

    private SaleDao(){}
    private static final SaleDao singleObject = new SaleDao();
    public static SaleDao getSingleObject(){
        return singleObject;
    }

    public int save(Sale sale)throws SQLException {
        String sqlQuery = "insert into sales (userId, totalPrice, saleTime) values (?,?,?)";
        int id = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setInt(1, sale.getUserId());
            preparedStatement.setDouble(2, sale.getTotalPrice());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(sale.getSaleTime()));
            preparedStatement.executeUpdate();
            ResultSet result = preparedStatement.getGeneratedKeys();
            if(result.next()){
                id = result.getInt(1);
            }
        }
        return id;
    }

    public SaleDto findBySaleId(int id)throws SQLException {
        String sqlQuery = "select * from sales where id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setInt(1,id);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()){
                return saleMapper.toDto(result);
            }
        }
        return null;
    }

    public List<SaleDto> findAllByUserId(int userId)throws SQLException {
        String sqlQuery = "select * from sales where userId=?";
        List<SaleDto> saleRecords = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setInt(1,userId);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                saleRecords.add(saleMapper.toDto(result));
            }
        }
        return saleRecords;
    }

}
