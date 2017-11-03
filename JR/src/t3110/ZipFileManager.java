package t3110;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by DELL on 11/1/2017.
 */
public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception{


        try(ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(source))) {

            ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());

            zipOutputStream.putNextEntry(zipEntry);
           
//****************
            zipOutputStream.write(Files.readAllBytes(source));

//****************
            /*InputStream inputStream = Files.newInputStream(source.getFileName());
            int i;
            while ((i = inputStream.read()) != -1) {
                zipOutputStream.write(i);
            }*/
        }

    }
}
