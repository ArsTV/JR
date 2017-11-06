package t3110;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import t3110.exception.PathIsNotFoundException;
import t3110.exception.WrongZipFileException;

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

    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception{
        try(InputStream inputStream = Files.newInputStream(filePath.resolve(fileName))) {

            ZipEntry zipEntry = new ZipEntry(fileName.toString());
            zipOutputStream.putNextEntry(zipEntry);        

            copyData(inputStream, zipOutputStream);

            zipOutputStream.closeEntry();
        }

    }

    private void copyData(InputStream in, OutputStream out) throws Exception{
        /*int temp;
        while((temp = in.read()) != -1){
            out.write(temp);
        }*/
    	byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
    
    public List<FileProperties> getFilesList() throws Exception{

        if(!Files.isRegularFile(zipFile)){
            throw  new WrongZipFileException();
        }

        List<FileProperties> filePropertiesList = new ArrayList<>();

        try(ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))){
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                copyData(zipInputStream, out);

                FileProperties fileProperties = new FileProperties(
                        zipEntry.getName(), zipEntry.getSize(), zipEntry.getCompressedSize(), zipEntry.getMethod());

                filePropertiesList.add(fileProperties);
            }
        }
        return filePropertiesList;
    }

}
