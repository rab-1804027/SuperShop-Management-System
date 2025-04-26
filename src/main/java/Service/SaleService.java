package Service;

import Dao.SaleDao;
import Model.Sale;

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

}
