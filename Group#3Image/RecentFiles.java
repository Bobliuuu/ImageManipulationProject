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

        // Checks if the list of files is empty. If it is, load the Array List
        // of files. 
        if (filelist == null) 
            filelist = loadList();
        
        // Returns the list of files. 
        return filelist;  
    }

    /**
     * Adds a file to the top of the recently opened filelist. Then stores the file.
     * 
     * @param filename The name of the absolute path of the file that will be stored in the recently opened file.
     */
    public void addRecentlyOpenedFile(String filename) {

        // Load the filelist from the filesystem if the fileList is empty/null.
        if (filelist == null) 
            filelist = loadList();

        // If the file is already in the fileList, remove it.
        if (filelist.contains(filename)) {
            filelist.remove(filename);
        }

        // add to front of the recently opened file list.
        filelist.add(0, filename);

        // save the fileList array to disk.
        saveList();
    }

    /**
     * This private helper method is used to save the arrayList of recently opened files to a txt file. 
     * Most recent will be at the top of the file.
     * 
     */
    private void saveList() {
        try {
            // https://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
            FileWriter writer = new FileWriter(RECENTFILES); 

            // Iterate through the file list. Store the first 8 or MAXFILES items to the text file.
            for (int i = 0; i < Math.min(filelist.size(), MAXFILES) ; i++) {
                writer.write(filelist.get(i) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
         
    }

    /**
     * This private helper method is used to load the recently opened files from a txt file into an ArrayList of Strings.
     * 
     * @return ArrayList<String> of recently opened absolute file paths.
     */
    private ArrayList<String> loadList() {

        try {
            // read all files from a text file and cast the List as an ArrayList
            ArrayList<String> list = (ArrayList<String>) Files.readAllLines(new File(RECENTFILES).toPath(), Charset.defaultCharset());
            return list;

        } catch (IOException e) { 
            // if the recent txt file is missing or empty, then return a new ArrayList.
            return new ArrayList<String>();
        }
    }

}
