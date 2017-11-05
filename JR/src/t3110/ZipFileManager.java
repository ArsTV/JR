package t3110;


import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import t3110.exception.PathIsNotFoundException;

/**
 * Created by DELL on 11/1/2017.
 */
public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception{
        Path filePath = source.getParent();
        Path fileName = source.getFileName();
        Path zipDirectory = zipFile.getParent();

        //if zip directory is not exists we'll create it
        if(Files.notExists(zipDirectory)){
            Files.createDirectories(zipDirectory);
        }

        // create zip output stream
        ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));

        // if source path it's file address
        if(Files.isRegularFile(source)) {
            addNewZipEntry(zipOutputStream, filePath, fileName);

            // if source path it's directory address
        } else if(Files.isDirectory(source)){
            //take list of files in the directory
            FileManager fileManager = new FileManager(source);
            List<Path> fileNames = fileManager.getFileList();

            // adding all the files from directory to archive
            for(Path file: fileNames){
                addNewZipEntry(zipOutputStream, source, file);
            }

            // if source path it's not file address and not directory address
        } else{
            throw new PathIsNotFoundException();
        }

        /*ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());

        zipOutputStream.putNextEntry(zipEntry);

        InputStream inputStream = Files.newInputStream(source);
            int i = 0;
            while ((i = inputStream.read()) != -1) {
                zipOutputStream.write(i);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            inputStream.close();*/
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception{
        try(InputStream inputStream = Files.newInputStream(filePath.resolve(fileName))) {

            ZipEntry zipEntry = new ZipEntry(fileName.toString());
            zipOutputStream.putNextEntry(zipEntry);

            /*int temp;
            while ((temp = inputStream.read()) != -1) {
                zipOutputStream.write(temp);
            }*/

            copyData(inputStream, zipOutputStream);

            zipOutputStream.closeEntry();
        }

    }

    private void copyData(InputStream in, OutputStream out) throws Exception{
        int temp;
        while((temp = in.read()) != -1){
            out.write(temp);
        }
    }

}
