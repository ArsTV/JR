package t2027;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    private static List<File> fileList = new ArrayList<>();

    public static void main(String[] args) {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");

        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        try(FileOutputStream fos = new FileOutputStream(allFilesContent)){
            fileTree(path);
            fileList.sort(new FileCompare());

            FileInputStream fis = null;
            for(File f: fileList){
                fis = new FileInputStream(f);

                while(fis.available() > 0 ){
                    fos.write(fis.read());
                }
                fos.write(System.lineSeparator().getBytes());

            }
            fos.flush();
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void fileTree(File file){
        File[] files = file.listFiles();

        for(File f: files){
            if(f.isDirectory()){
                fileTree(f);
                continue;
            }

            if (f.length() > 50 )
                FileUtils.deleteFile(f);
            else
                fileList.add(f);
        }
    }

}

class FileCompare implements Comparator<File>{

    @Override
    public int compare(File o1, File o2) {
        return o1.getName().compareTo(o2.getName());
    }
}