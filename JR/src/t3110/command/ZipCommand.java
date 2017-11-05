package t3110.command;

import java.nio.file.Path;
import java.nio.file.Paths;

import t3110.ConsoleHelper;
import t3110.ZipFileManager;

public abstract class ZipCommand implements Command{

    public ZipFileManager getZipFileManager() throws Exception{
        ConsoleHelper.writeMessage("You need to write full path");
        String fullPathString = ConsoleHelper.readString();
        Path fullPath = Paths.get(fullPathString);
        return new ZipFileManager(fullPath);
    }
}
