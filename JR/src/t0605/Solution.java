package t0605;

import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
        double weight = Double.parseDouble(bis.readLine());
        double height = Double.parseDouble(bis.readLine());

        Body.massIndex(weight, height);
    }

    public static class Body {
        public static void massIndex(double weight, double height) {
        	double d = weight / (height * height);

            if(d < 18.5)
                System.out.println("Underweight: less than 18.5");
            else if(d >= 18.5 && d <= 24.9)
                System.out.println("Normal: between 18.5 and 24.9");
            else if(d >= 25 && d <= 29.9)
                System.out.println("Overweight: between 25 and 29.9");
            else if(d >= 30)
                System.out.println("Obesity: 30 or more");            
        }
    }
}
