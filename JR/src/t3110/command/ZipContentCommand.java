package t3110.command;

import java.util.List;

import t3110.ConsoleHelper;
import t3110.FileProperties;
import t3110.ZipFileManager;

/**
 * Created by DELL on 11/3/2017.
 */
public class ZipContentCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Scan contents of the archive.");

        ZipFileManager zipFileManager = getZipFileManager();

        ConsoleHelper.writeMessage("Contents of the archive:");

        List<FileProperties> filePropertiesList = zipFileManager.getFilesList();

        for(FileProperties f: filePropertiesList){
            ConsoleHelper.writeMessage(f.toString());
        }

        ConsoleHelper.writeMessage("Contents of the archive was read.");


    }
}
