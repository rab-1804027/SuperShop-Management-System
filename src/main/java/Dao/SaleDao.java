package Dao;

import Config.DbConfig;
import Model.Sale;

import java.sql.*;

public class SaleDao {
    Connection connection = DbConfig.connectDb();

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
}
