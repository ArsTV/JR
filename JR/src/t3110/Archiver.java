package t3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import t3110.command.ExitCommand;


/**
 * Created by DELL on 11/1/2017.
 */
public class Archiver {
    public static void main(String[] args) throws IOException, Exception {
        System.out.println("Enter full path of the archive.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();

        Path path1 = Paths.get(path);

        ZipFileManager zipFileManager = new ZipFileManager(path1);

        //Path path2 = Paths.get(reader.readLine());

        zipFileManager.createZip(path1);

        reader.close();
        
        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute();

    }
}
