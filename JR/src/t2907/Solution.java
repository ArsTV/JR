package t2907;

import java.math.BigDecimal;

public class Solution {
    public static void main(String[] args) {

        System.out.println(getValue(1.1d, 1.2d));
    }

    public static BigDecimal getValue(double v1, double v2) {
        return new BigDecimal(v1).add(new BigDecimal(v2));
    }
}