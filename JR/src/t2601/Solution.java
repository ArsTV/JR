package t2601;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Integer[] i = {13, 8, 15, 5, 17};

        for (Integer ir : i) {
            System.out.print(ir + ", ");
        }

        System.out.println();
        Integer[] f = new Integer[i.length];
        f = sort(i);

        for (int j = 0; j < f.length; j++) {
            System.out.print(f[j] + ", ");
        }
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here


//-----------------------------------------------------------
        Arrays.sort(array);
        int len = array.length;
        int middle = len % 2 != 0 ? array[len / 2] : (int)((array[len / 2] + array[len / 2 - 1]) / 2);

        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return Math.abs(middle - o1) - Math.abs(middle - o2);
            }
        };

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
        Collections.sort(list, comp);

        return list.toArray(new Integer[list.size()]);
//-----------------------------------------------------------
        /*int middle = 0;
        int midlePos = array.length/2;

        Arrays.sort(array);

        if(array.length%2 ==0){
            middle = (array[midlePos] + array[midlePos + 1])/2;
        } else{
            middle = array[midlePos];
        }

//        System.out.println(middle);

        int finalMiddle = middle;
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(finalMiddle - o1) - Math.abs(finalMiddle - o2);
            }
        };

        List< Integer > list = Arrays.asList(array);

        Set< Integer > set = new TreeSet< Integer >(comparator);

        set.addAll(list);

        return set.toArray(new Integer[set.size()]);*/
    }
}
