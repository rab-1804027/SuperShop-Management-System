package Service;

import Dao.SaleDao;
import Dto.SaleDto;
import Model.Sale;

import java.sql.SQLException;
import java.util.List;

public class SaleService {

    private SaleService(){}
    private static final SaleService singleObject = new SaleService();
    public static SaleService getSingleObject(){
        return singleObject;
    }

    SaleDao saleDao = SaleDao.getSingleObject();

    public int save(Sale sale)throws Exception{
        return saleDao.save(sale);
    }

    public List<SaleDto> findAllByUserId(int userId)throws SQLException {
        return saleDao.findAllByUserId(userId);
    }

}
