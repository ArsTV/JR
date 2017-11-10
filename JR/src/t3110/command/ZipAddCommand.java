package t3110.command;

import java.nio.file.Path;
import java.nio.file.Paths;

import t3110.ConsoleHelper;
import t3110.ZipFileManager;
import t3110.exception.PathIsNotFoundException;

/**
 * Created by DELL on 11/3/2017.
 */

public class ZipAddCommand extends ZipCommand{

	@Override
	public void execute() throws Exception {
		try {
            ConsoleHelper.writeMessage("Adding new file.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Write full address of the file:");
            Path sourcePath = Paths.get(ConsoleHelper.readString());
            zipFileManager.addFile(sourcePath);

            ConsoleHelper.writeMessage("The file added!");

        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("The file or the directory was not found.");
        }
	}

}
