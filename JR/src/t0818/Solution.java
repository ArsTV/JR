package t0818;

import java.util.*;

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> list = new HashMap<>();
        list.put("A", 1);
        list.put("B", 1000);
        list.put("C", 150);
        list.put("D", 1);
        list.put("E", 1);
        list.put("F", 1);
        list.put("G", 19);
        list.put("H", 1);
        list.put("I", 1);
        list.put("J", 1);
        return list;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        HashMap<String, Integer> copy = new HashMap<>(map);
        for(Map.Entry<String, Integer> entry: copy.entrySet()){
            if(entry.getValue() < 500){
                map.remove(entry.getKey());
            }
        }

    }

    public static void main(String[] args) {

    }
}