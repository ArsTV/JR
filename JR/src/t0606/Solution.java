package t0606;


import java.io.*;
import java.util.Scanner;

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        String [] line = scanner.nextLine().split("");
        for (int i = 0; i < line.length; i++) {
            if((Integer.parseInt(line[i]) %2) == 0){
                even++;
            } else {
                odd++;
            }
        }
        System.out.println("Even: " + even + " Odd: " + odd);
    }
}
