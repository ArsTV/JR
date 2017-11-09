package t3110.command;

import java.nio.file.Path;
import java.nio.file.Paths;

import t3110.ConsoleHelper;
import t3110.ZipFileManager;

/**
 * Created by DELL on 11/3/2017.
 */
public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
    	ConsoleHelper.writeMessage("Delet from archive.");

        ZipFileManager zipFileManager = getZipFileManager();

        ConsoleHelper.writeMessage("Write full name of the file in the archive:");
        Path sourcePath = Paths.get(ConsoleHelper.readString());
        zipFileManager.removeFile(sourcePath);

        ConsoleHelper.writeMessage("The file deleted.");
    }
}
