package t3110.command;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import t3110.ConsoleHelper;
import t3110.FileProperties;
import t3110.ZipFileManager;
import t3110.exception.PathIsNotFoundException;

/**
 * Created by DELL on 11/3/2017.
 */
public class ZipExtractCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Extracting archive");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("You need to write full name of file or directory for extracting to it.");
            Path path = Paths.get(ConsoleHelper.readString());

            zipFileManager.extractAll(path);

            ConsoleHelper.writeMessage("Archive exctracted");
        }catch (PathIsNotFoundException e){
            ConsoleHelper.writeMessage("Wrong file name or directory name.");
        }
    }
}