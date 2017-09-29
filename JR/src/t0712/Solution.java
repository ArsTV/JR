package t0712;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        //whrite here your code
            int min = 0;
            int max = 0;
            Scanner scanner = new Scanner(System.in);
            ArrayList<String> list = new ArrayList();
            for (int i = 0; i < 10; i++) {
                list.add(scanner.next());
            }
            for (int i = 1; i < list.size(); i++) {
                if (list.get(max).length() < list.get(i).length()) {
                    max = i;
                }
                if (list.get(min).length() > list.get(i).length()) {
                    min = i;
                }
            }
            if (max < min) {
                System.out.println(list.get(max));
            } else
                System.out.println(list.get(min));
    }
}
