package t3110;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 11/4/2017.
 */
public class FileManager {

    private Path rootPath;
    private List<Path> fileList;

    public FileManager(Path rootPath) throws IOException{
        this.rootPath = rootPath;
        fileList = new ArrayList<>();
        collectFileList(rootPath);
    }

    public List<Path> getFileList() {
        return fileList;
    }

    // the method will stack all the files found the passed path
    private void collectFileList(Path path) throws IOException{

        if(Files.isRegularFile(path)){
            Path relativePath = rootPath.relativize(path);
            fileList.add(relativePath);
        }

        if(Files.isDirectory(path)){
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
            for(Path filePath: directoryStream){
                collectFileList(filePath);
            }
        }
    }
}
