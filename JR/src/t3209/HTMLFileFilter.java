package t3209;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Created by DELL on 1/6/2018.
 */
public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File pathname) {
        if(pathname.isDirectory() || (pathname.getName().toLowerCase().endsWith(".html") ||
                pathname.getName().toLowerCase().endsWith(".htm")))
            return true;
        else
        return false;
    }

    @Override
    public String getDescription() {
        return "HTML and HTM files";
    }
}
