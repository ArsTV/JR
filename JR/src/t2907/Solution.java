package t2907;

import java.math.BigDecimal;

public class Solution {
    public static void main(String[] args) {

        System.out.println(getValue(1.1d, 1.2d));
    }

    public static BigDecimal getValue(double v1, double v2) {Double d1 = v1;
    Double d2 = v2;
    return new BigDecimal(d1.toString()).add(new BigDecimal(d2.toString()));

    //or using String.valueOf:
    //return new BigDecimal(String.valueOf(v1)).add(new BigDecimal(String.valueOf(v2)));
    }
}