package t3110;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
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
    
    public void extractAll(Path outputFolder) throws Exception{
    	
        if(!Files.isRegularFile(zipFile)){
            throw new WrongZipFileException();
        }
        
        //Creating directory if folder is not exist
        if(Files.notExists(outputFolder)){
            Files.createDirectories(outputFolder);
        }
        try(ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))) {

            ZipEntry zipEntry;
            
            //extracting all files to the folder
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String fileName = zipEntry.getName();
                Path fullPath = outputFolder.resolve(fileName);

                Path parentPAth = fullPath.getParent();
                if(Files.notExists(parentPAth)){
                    Files.createDirectories(parentPAth);
                }

                try(OutputStream outputStream = Files.newOutputStream(fullPath)){
                    copyData(zipInputStream, outputStream);
                }
            }
        }
    }
    

    public void removeFiles(List<Path> pathList) throws Exception{
        if(!Files.isRegularFile(zipFile)){
            throw new WrongZipFileException();
        }

        Path tempFile = Files.createTempFile(null, null);

        try(ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(tempFile))){
            try(ZipInputStream in = new ZipInputStream(Files.newInputStream(zipFile))){

                ZipEntry zipEntry = in.getNextEntry();

                //copy only necessaire files
                while (zipEntry != null){
                    Path archiveFile = Paths.get(zipEntry.getName());

                    if(!pathList.contains(archiveFile)){
                        String fileName = zipEntry.getName();
                        out.putNextEntry(new ZipEntry(fileName));

                        copyData(in, out);

                        in.getNextEntry();
                        out.closeEntry();
                    }
                    else{
                        ConsoleHelper.writeMessage("File " + archiveFile + " was deleted from the archive.");
                    }
                    zipEntry = in.getNextEntry();
                }
            }
        }
        
        // copy from temp file to the new zip archive file
        Files.move(tempFile, zipFile, StandardCopyOption.REPLACE_EXISTING);
    }
    

    public void removeFile(Path path) throws Exception{
        removeFiles(Collections.singletonList(path));
    }

}
