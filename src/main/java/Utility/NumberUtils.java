package Utility;

public class NumberUtils {
    public static boolean isNullOrInvalid(Double input)
    {
        if(input == null)
            return true;
        else
            return input <= 0.0;
    }

    public static boolean isNullOrNegative(Double input)
    {
        if(input == null)
            return true;
        else
            return input < 0.0;
    }

}
