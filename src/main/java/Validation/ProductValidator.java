package Validation;

import Utility.NumberUtils;
import Utility.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class ProductValidator {

    private ProductValidator(){}
    private static final ProductValidator singleObject = new ProductValidator();
    public static ProductValidator getSingleObject(){
        return singleObject;
    }

    public Map<String, String> validate(String name, Double price, Double stockQuantity) {
        Map<String, String> errors = new HashMap<>();
        if(StringUtils.isNullorEmpty(name)) {
            errors.put("name", "Name cannot be empty or null");
        }
        if(NumberUtils.isNullOrInvalid(price)) {
            errors.put("price", "Price should be a positive number");
        }
        if(NumberUtils.isNullOrNegative(stockQuantity)) {
            errors.put("stockQuantity", "Stock quantity cannot be null or negative");
        }
        return errors;

    }
}
