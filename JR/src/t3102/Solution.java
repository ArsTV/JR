package t3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Find all files
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {

        File file = new File(root);
        List<String> dirList = new ArrayList<>();
        Queue<File> fileQueue = new LinkedList<>();

        Collections.addAll(fileQueue, file.listFiles());

        while (!fileQueue.isEmpty()) {
            File fileTemp = fileQueue.remove();

            if (fileTemp.isDirectory()) {
                Collections.addAll(fileQueue, fileTemp.listFiles());
            } else {
                dirList.add(fileTemp.getAbsolutePath());
            }
        }

        return dirList;

    }

    public static void main(String[] args) throws IOException {
        List<String> list = getFileTree("C:/Test");

        for (String s : list) {
            System.out.println(s);
        }
    }
}