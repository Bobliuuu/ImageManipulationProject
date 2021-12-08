import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
/**
 * Starter code for Image Manipulation Array Assignment.
 * 
 * The class Processor contains all of the code to actually perform
 * transformation. The rest of the classes serve to support that
 * capability. This World allows the user to choose transformations
 * and open files.
 * 
 * Add to it and make it your own!
 * 
 * @author Jordan Cohen
 * @version November 2013
 */
public class Background extends World
{
    // Constants:
    private final String STARTING_FILE = "colourful.jpg";
    public static final int MAX_WIDTH = 800;
    public static final int MAX_HEIGHT = 720;

    // Objects and Variables:
    private ImageHolder image;

    private BufferedImage original;

    private ArrayList<BufferedImage> edits;

    private int editPos;
    
    private TextButton blueButton, hRevButton, openButton, rotateButton, vRevButton, negativeButton, brightButton, darkButton, blue, red, green, yellow, resetButton, saveButton;
    
    private TextButtonPicture bluePicture, redPicture, greenPicture, yellowPicture;

    private SuperTextBox openFile, saveFile;

    private String fileName;

    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 

        // Initialize buttons and the image
        image = new ImageHolder(STARTING_FILE); // The image holder constructor does the actual image loading
        
        // Set up UI elements
        blueButton = new TextButton("Blueify", 8, 160, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
    
        // blueButton.setFixedWidth(160); // setting a fixed width so buttons will be the same width
        hRevButton = new TextButton("Flip Horizontal", 8, 160, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        vRevButton = new TextButton("Flip Vertical", 8, 160, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        resetButton = new TextButton("Reset", 8, 160, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        openButton = new TextButton ("Open", 8, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        //openButton.setFixedWidth(80);
        rotateButton = new TextButton("Rotate Clockwise", 8, 160, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        negativeButton = new TextButton("Negative", 8, 160, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        brightButton = new TextButton("Brighten", 8, 160, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        darkButton = new TextButton("Darken", 8, 160, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        saveButton = new TextButton ("Save", 8, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        
        blue = new TextButton (" ");
        red = new TextButton (" ");
        green = new TextButton (" ");
        yellow = new TextButton (" ");
        
        bluePicture = new TextButtonPicture(Color.BLUE);
        redPicture = new TextButtonPicture(Color.RED);
        greenPicture = new TextButtonPicture(Color.GREEN);
        yellowPicture = new TextButtonPicture(Color.YELLOW);
        
        openFile = new SuperTextBox(new String[]{"File: " + STARTING_FILE,"Scale: " + image.getScale() + " W: " + image.getRealWidth() + " H: " + image.getRealHeight()}, new Font ("Comic Sans MS", false, false, 16), 600, true);//new TextButton(" [ Open File: " + STARTING_FILE + " ] ");
        saveFile = new SuperTextBox(new String[]{"File: " + STARTING_FILE,"Scale: " + image.getScale() + " W: " + image.getRealWidth() + " H: " + image.getRealHeight()}, new Font ("Comic Sans MS", false, false, 16), 600, true);//new TextButton(" [ Open File: " + STARTING_FILE + " ] ");

        // Add objects to the screen
        addObject (image, 430, 430);
        addObject (blueButton, 940, 24);
        addObject (hRevButton, 940, 66);
        addObject (rotateButton, 940, 108);
        addObject (vRevButton, 940, 150);
        addObject (resetButton, 940, 192);
        addObject (negativeButton, 780, 24);
        addObject (brightButton, 780, 66);
        addObject (darkButton, 780, 108);
        addObject (blue, 940, 390);
        addObject (red, 940, 246);
        addObject (green, 940, 294);
        addObject (yellow, 940, 342);
        addObject (bluePicture, blue.getX(), blue.getY());
        addObject (redPicture, red.getX(), red.getY());
        addObject (greenPicture, green.getX(), green.getY());
        addObject (yellowPicture, yellow.getX(), yellow.getY());
        
        // place the open file text box in the top left corner
        addObject (openFile, openFile.getImage().getWidth() / 2, openFile.getImage().getHeight() / 2);
        // place the open file button directly beside the open file text box
        addObject (openButton, openFile.getImage().getWidth()  + openButton.getImage().getWidth()/2 + 2, openFile.getImage().getHeight() / 2);
        // place the save file button 
        addObject (saveButton, saveFile.getImage().getWidth()  + saveButton.getImage().getWidth()/2 + 2, (saveFile.getImage().getHeight() / 2) + 42);
        
        editPos = -1;
        original = deepCopy(image.getBufferedImage());
    }

    /**
     * Act() method just checks for mouse input
     */
    public void act ()
    {
        checkMouse();
    }

    /**
     * Check for user clicking on a button
     */
    private void checkMouse ()
    {
        // Avoid excess mouse checks - only check mouse if somethething is clicked.
        if (Greenfoot.mouseClicked(null))
        {
            if (Greenfoot.mouseClicked(blueButton)){
                Processor.blueify(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
            }
            else if (Greenfoot.mouseClicked(hRevButton)){
                Processor.flipHorizontal(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                //BufferedImage temp = Processor.rotate90Clockwise (image.getBufferedImage());
                //image.setImage(createGreenfootImageFromBI (temp));
            }
            else if (Greenfoot.mouseClicked(vRevButton)){
                Processor.flipVertical(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                //BufferedImage temp = Processor.rotate90Clockwise (image.getBufferedImage());
                //image.setImage(createGreenfootImageFromBI (temp));
            }
            else if (Greenfoot.mouseClicked(rotateButton)){
                // unfinished
            }
            else if (Greenfoot.mouseClicked(resetButton)){
                image.setImage(createGreenfootImageFromBI(original));
                openFile.update (image.getDetails ());
            }
            else if (Greenfoot.mouseClicked(negativeButton)){
                Processor.negative(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
            }
            else if (Greenfoot.mouseClicked(brightButton)){
                Processor.brighten(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
            }
            else if (Greenfoot.mouseClicked(darkButton)){
                Processor.darken(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
            }
            else if (Greenfoot.mouseClicked(openButton))
            {
                openFile ();
            }

            else if (Greenfoot.mouseClicked(saveButton))
            {
                saveFile ();
            }
        }
    }

    // Code provided, but not yet implemented - This will save image as a png.
    private void saveFile () {
        try{
            // This will pop up a text input box - You should improve this with a JFileChooser like for the open function
            String fileName = JOptionPane.showInputDialog("Input file name (no extension)");

            // fileName += ".png";
            // File f = new File (fileName);  
            // ImageIO.write(image.getBufferedImage(), "png", f); 

            fileName += ".jpg";
            BufferedImage bi = image.getBufferedImage();

            // https://stackoverflow.com/questions/16002167/using-imageio-write-to-create-a-jpeg-creates-a-0-byte-file
            BufferedImage newBufferedImage = new BufferedImage(bi.getWidth(),
            bi.getHeight(), BufferedImage.TYPE_INT_RGB);
            newBufferedImage.getGraphics().drawImage(bi, 0, 0, null);

            File outputfile = new File(fileName);
            // https://docs.oracle.com/javase/tutorial/2d/images/saveimage.html
            ImageIO.write(newBufferedImage, "jpg", outputfile);


        }
        catch (IOException e){
            // this code instead
            System.out.println("Unable to save file: " + e);
        }
    }

    /**
     * Allows the user to open a new image file.
     */
    private void openFile ()
    {
        // Create a UI frame (required to show a UI element from Java.Swing)
        JFrame frame = new JFrame();
        // Create a JFileChooser, a file chooser box included with Java 
        JFileChooser fileChooser = new JFileChooser();

        // Add File filter for PNG and JPEG.
        // https://stackoverflow.com/questions/19302029/filter-file-types-with-jfilechooser
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG file", new String[] {"png"}));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG file", new String[] {"jpg", "jpeg"}));

        //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            if (image.openFile (selectedFile.getAbsolutePath(), selectedFile.getName()))
            {
                //String display = " [ Open File: " + selectedFile.getName() + " ] ";
                openFile.update (image.getDetails ());
            }
        }
        // If the file opening operation is successful, update the text in the open file button
        /**if (image.openFile (fileName))
        {
        String display = " [ Open File: " + fileName + " ] ";
        openFile.update (display);
        }*/
        
    }

    /**
     * Takes in a BufferedImage and returns a GreenfootImage.
     * 
     * @param newBi The BufferedImage to convert.
     * 
     * @return GreenfootImage   A GreenfootImage built from the BufferedImage provided.
     */
    public static GreenfootImage createGreenfootImageFromBI (BufferedImage newBi)
    {
        GreenfootImage returnImage = new GreenfootImage (newBi.getWidth(), newBi.getHeight());
        BufferedImage backingImage = returnImage.getAwtImage();
        Graphics2D backingGraphics = (Graphics2D)backingImage.getGraphics();
        backingGraphics.drawImage(newBi, null, 0, 0);

        return returnImage;
    }
    
    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultip = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultip, null);
    }
}

