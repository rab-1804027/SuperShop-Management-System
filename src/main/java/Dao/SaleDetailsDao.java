package Dao;

import Config.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaleDetailsDao {
    Connection connection = DbConfig.connectDb();

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
}
