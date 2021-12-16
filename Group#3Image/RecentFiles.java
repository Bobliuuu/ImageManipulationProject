import java.util.ArrayList;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>Useful methods to open recent files. Recent files works by saving 
 * all recently opened files (as they open) in a text file and then retrieves them. 
 * New files that have already been opened appear at the top of the text file.</p>
 * 
 * @author Ibrahim Rahman 
 * @version December 2021
 */
public class RecentFiles {
    
    // Maximum amount of files that can be stored. 
    private static int MAXFILES = 8;
    
    // Name of text file containing the list of recent files. 
    private static String RECENTFILES = "recentfiles.txt";

    // Array List of names of recently opened files. 
    private ArrayList<String> filelist;

    /**
     * Gets an Array List of all recently opened files. 
     * 
     * @return Array List of files that have recently been opened. 
     */
    public ArrayList<String> getRecentFiles() {

        // Checks if the list of files is emty. If it is, load the Array List
        // of files. 
        if (filelist == null) 
            filelist = loadList();
        
        // Returns the list of files. 
        return filelist;  
    }

    public void addRecentlyOpenedFile(String filename) {
        if (filelist == null) 
            filelist = loadList();

        if (filelist.contains(filename)) {
            filelist.remove(filename);
        }
        // add to front of the list.
        filelist.add(0, filename);
        saveList();
    }

    // https://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
    private void saveList() {
        try {
            FileWriter writer = new FileWriter(RECENTFILES); 

            for (int i = 0; i < Math.min(filelist.size(), MAXFILES) ; i++) {
                writer.write(filelist.get(i) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
         
    }

    private ArrayList<String> loadList() {

        try {
            ArrayList<String> list = (ArrayList<String>) Files.readAllLines(new File(RECENTFILES).toPath(), Charset.defaultCharset());
            return list;
        } catch (IOException e) {            
            return new ArrayList<String>();
        }
    }

}
