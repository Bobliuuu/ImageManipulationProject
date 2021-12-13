import java.util.ArrayList;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RecentFiles {
    
    private static int MAXFILES = 8;
    private static String RECENTFILES = "recentfiles.txt";
    private ArrayList<String> filelist;


    public ArrayList<String> getRecentFiles() {
        if (filelist == null) 
            filelist = loadList();
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
