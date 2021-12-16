import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.lang.Math;
/**
 * Starter code for Image Manipulation Array Assignment.
 * 
 * The class Background contains all of the code to actually perform
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
    private final String STARTING_FILE = "mountain.jpg";
    
    public static final int MAX_WIDTH = 800;
    public static final int MAX_HEIGHT = 720;

    public static final Color PURPLE = new Color(102, 0, 153);
    public static final Color BROWN = new Color(102, 51, 0);
    
    // Objects and Variables:
    private ImageHolder image;

    private BufferedImage original, stampImage;
    
    private GreenfootImage testHolder;

    private ArrayList<BufferedImage> edits = new ArrayList<BufferedImage>();

    private int editPos;
    
    private boolean inCropOne, inCropTwo, stamping;
    
    private TextButton blueButton, hRevButton, openButton, rotateButton, vRevButton, negativeButton, brightButton, darkButton, resetButton, saveButton, rotateOtherButton, undoButton, redoButton, moreTransparent, lessTransparent, recentFilesButton;
    
    private TextButton pixelateButton, blurButton, warmButton, coolButton, gaussianButton, sepiaButton, contrastButton, hueButton, swapRGBButton, sharpenButton, cropButton, firstPoint, secondPoint, greyScaleButton, solarizeButton, noiseButton, laplaceButton;
    
    private TextButton stampButton;
    
    private ColorButton bluePicture, redPicture, greenPicture, yellowPicture, orangePicture, pinkPicture, grayPicture, blackPicture, purplePicture, brownPicture, topBar, cropBox;

    private SuperTextBox openFile, saveFile, colorifyLabel;

    private String fileName;

    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 900, 1); 

        // Initialize buttons and the image
        testHolder = new GreenfootImage("coin.png");
        stampImage = testHolder.getAwtImage();
        image = new ImageHolder(STARTING_FILE); // The image holder constructor does the actual image loading
        
        // Set up UI elements
        hRevButton = new TextButton("Flip Horizontal", 5, 110, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        vRevButton = new TextButton("Flip Vertical", 5, 110, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        resetButton = new TextButton("Reset", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        openButton = new TextButton ("Open", 5, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        recentFilesButton = new TextButton ("Open Recent File", 5, 100, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));

        //openButton.setFixedWidth(80);
        rotateButton = new TextButton("Rotate Clockwise", 5, 120, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        rotateOtherButton = new TextButton("Rotate Counterclockwise", 5, 140, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));        
        negativeButton = new TextButton("Negative", 5, 90, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        brightButton = new TextButton("Brighten", 5, 90, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        darkButton = new TextButton("Darken", 5, 90, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        saveButton = new TextButton ("Save", 5, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        undoButton = new TextButton("Undo", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        redoButton = new TextButton("Redo", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        moreTransparent = new TextButton("More Transparent", 5, 110, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        lessTransparent = new TextButton("Less Transparent", 5, 120, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        pixelateButton = new TextButton("Pixelate", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        blurButton = new TextButton("Blur", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        warmButton = new TextButton("Warmer", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        coolButton = new TextButton("Cooler", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        gaussianButton = new TextButton("Gaussian Blur", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        sepiaButton = new TextButton("Sepia", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        contrastButton = new TextButton("Change Contrast", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        hueButton = new TextButton("Change Hue", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        swapRGBButton = new TextButton("Swap RGB", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        sharpenButton = new TextButton("Sharpen", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        cropButton = new TextButton("Crop", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        greyScaleButton = new TextButton("Greyscale", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        solarizeButton = new TextButton("Solarize", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        noiseButton = new TextButton("Noise", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        laplaceButton = new TextButton("Lens Flare", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        stampButton = new TextButton("Stamp", 5, 90, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
        
        
        // Builtin colors
        bluePicture = new ColorButton(Color.BLUE);
        redPicture = new ColorButton(Color.RED);
        greenPicture = new ColorButton(Color.GREEN);
        yellowPicture = new ColorButton(Color.YELLOW);
        orangePicture = new ColorButton(Color.ORANGE);
        pinkPicture = new ColorButton(Color.PINK);
        grayPicture = new ColorButton(Color.GRAY);
        blackPicture = new ColorButton(Color.BLACK);
        
        
        // Custom colours
        purplePicture = new ColorButton(PURPLE);
        brownPicture = new ColorButton(BROWN);
        
        topBar = new ColorButton(Color.BLACK, 1280, 166, false);
        
        openFile = new SuperTextBox(new String[]{"File: " + STARTING_FILE,"Scale: " + image.getScale() + " W: " + image.getRealWidth() + " H: " + image.getRealHeight()}, new Font ("Comic Sans MS", false, false, 16), 600, true);//new TextButton(" [ Open File: " + STARTING_FILE + " ] ");
        colorifyLabel = new SuperTextBox(new String[]{"Colour-ify"}, Color.DARK_GRAY, Color.WHITE, new Font("Comic Sans MS", false, false, 13), true, 74, 1, Color.WHITE);
        
        // Add objects to the screen
        addObject (topBar, 640, 83);
        addObject (image, 640, 560);
        addObject (hRevButton, 162, 76);
        addObject (rotateButton, 292, 76);
        addObject (rotateOtherButton, 292, 108);
        addObject (vRevButton, 162, 108);
        addObject (resetButton, 412, 140);
        addObject (negativeButton, 52, 76);
        addObject (brightButton, 52, 108);
        addObject (darkButton, 52, 140);
        addObject (pixelateButton, 510, 76);
        addObject (blurButton, 510, 108);
        addObject (warmButton, 510, 140);
        addObject (coolButton, 608, 76);
        addObject (gaussianButton, 608, 108);
        addObject (sepiaButton, 608, 140);
        addObject (contrastButton, 706, 76);
        addObject (hueButton, 706, 108);
        addObject (swapRGBButton, 706, 140);
        addObject (sharpenButton, 804, 76);
        addObject (greyScaleButton, 804, 140);
        addObject (solarizeButton, 902, 76);
        addObject (noiseButton, 902, 108);
        addObject (laplaceButton, 902, 140);
        addObject (moreTransparent, 162, 140);
        addObject (lessTransparent, 292, 140);
        addObject (colorifyLabel, 1215, 16);
        addObject (brownPicture, 1165, 45);
        addObject (grayPicture, 1190, 45);
        addObject (redPicture, 1215, 45);
        addObject (yellowPicture, 1240, 45);
        addObject (bluePicture, 1265, 45);
        addObject (purplePicture, 1165, 70);
        addObject (blackPicture, 1190, 70);
        addObject (pinkPicture, 1215, 70);
        addObject (orangePicture, 1240, 70);
        addObject (greenPicture, 1265, 70);
        addObject (undoButton, 412, 76);
        addObject (redoButton, 412, 108);
        addObject (cropButton, 804, 108);
        addObject (stampButton, 1000, 76);
        
        // place the open file text box in the top left corner
        addObject (openFile, openFile.getImage().getWidth() / 2, openFile.getImage().getHeight() / 2);
        // place the open file button directly beside the open file text box
        addObject (openButton, openFile.getImage().getWidth()  + openButton.getImage().getWidth()/2 + 3, openFile.getImage().getHeight() / 2 - 10);
        
        addObject (recentFilesButton, openFile.getImage().getWidth() + saveButton.getImage().getWidth()/2 + 94, openFile.getImage().getHeight() / 2 - 10);
        
        // place the save file button 
        addObject (saveButton, openFile.getImage().getWidth() + saveButton.getImage().getWidth()/2 + 185, openFile.getImage().getHeight() / 2 - 10);
        
        
        editPos = 0;
        original = deepCopy(image.getBufferedImage());
        edits.add(deepCopy(original));
        inCropOne = false;
        inCropTwo = false;
        stamping = false;
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
            if (Greenfoot.mouseClicked(brownPicture)){
                Processor.brown(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(grayPicture)){
                Processor.gray(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(redPicture)){
                Processor.red(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(yellowPicture)){
                Processor.yellow(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(bluePicture)){
                Processor.blue(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(purplePicture)){
                Processor.purple(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(blackPicture)){
                Processor.black(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(pinkPicture)){
                Processor.pink(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(orangePicture)){
                Processor.orange(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(greenPicture)){
                Processor.green(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(hRevButton)){
                Processor.flipHorizontal(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                //BufferedImage temp = Processor.rotate90Clockwise (image.getBufferedImage());
                //image.setImage(createGreenfootImageFromBI (temp));
            }
            else if (Greenfoot.mouseClicked(vRevButton)){
                Processor.flipVertical(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
                //BufferedImage temp = Processor.rotate90Clockwise (image.getBufferedImage());
                //image.setImage(createGreenfootImageFromBI (temp));
            }
            else if (Greenfoot.mouseClicked(rotateButton)){
                // unfinished
                image.setFullImage(createGreenfootImageFromBI(Processor.rotate90Clockwise(image.getBufferedImage())));
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
            }
            else if (Greenfoot.mouseClicked(rotateOtherButton)){
                // unfinished
                image.setFullImage(createGreenfootImageFromBI(Processor.rotate90CounterClockwise(image.getBufferedImage())));
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
            }
            else if (Greenfoot.mouseClicked(resetButton)){
                //image.setImage(createGreenfootImageFromBI(original));
                //openFile.update (image.getDetails ());
                image.setFullImage(createGreenfootImageFromBI(original));
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(negativeButton)){
                Processor.negative(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(brightButton)){
                Processor.brighten(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(darkButton)){
                Processor.darken(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(moreTransparent)){
                Processor.moreTransparent(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(lessTransparent)){
                Processor.lessTransparent(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(pixelateButton)){
                Processor.pixelate(image.getBufferedImage(), 6);
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(blurButton)){
                Processor.gaussianBlur(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(undoButton)){
                if(editPos - 1 >= 0){
                    editPos--;
                    image.setFullImage(createGreenfootImageFromBI(edits.get(editPos)));
                    image.redraw();
                    openFile.update (image.getDetails ());
                }
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(redoButton)){
                if(editPos + 1 < edits.size()){
                    editPos++;
                    image.setFullImage(createGreenfootImageFromBI(edits.get(editPos)));
                    image.redraw();
                    openFile.update (image.getDetails ());
                }
                resetCrop();
            } 
            else if (Greenfoot.mouseClicked(warmButton)){
                Processor.warmer(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
            } 
            else if (Greenfoot.mouseClicked(coolButton)){
                Processor.cooler(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            } 
            else if (Greenfoot.mouseClicked(gaussianButton)){
                Processor.gaussianBlur(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            } 
            else if (Greenfoot.mouseClicked(sepiaButton)){
                Processor.sepia(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            } 
            else if (Greenfoot.mouseClicked(contrastButton)){
                Processor.contrast(image.getBufferedImage(), 0.9);
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            } 
            else if (Greenfoot.mouseClicked(hueButton)){
                Processor.adjustHue(image.getBufferedImage(), (float)0.1);
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            } 
            else if (Greenfoot.mouseClicked(swapRGBButton)){
                Processor.swapRGB(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(sharpenButton)){
                Processor.sharpen(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            } 
            else if (Greenfoot.mouseClicked(greyScaleButton)){
                Processor.grayScale(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            } 
            else if (Greenfoot.mouseClicked(solarizeButton)){
                Processor.solarize(image.getBufferedImage(), 1);
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            } 
            else if (Greenfoot.mouseClicked(noiseButton)){
                Processor.noise(image.getBufferedImage(), 5);
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            } 
            else if (Greenfoot.mouseClicked(laplaceButton)){
                Processor.laplace(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                checkForEdit();
                resetCrop();
            }
            else if (Greenfoot.mouseClicked(image)){
                if(stamping){
                    BufferedImage bi = image.getBufferedImage();
                    MouseInfo click = Greenfoot.getMouseInfo();
                    int topX = click.getX()-(640-(int)(bi.getWidth()/2)), topY = click.getY()-(560-(int)(bi.getHeight()/2));
                    if((topX + stampImage.getWidth() <= bi.getWidth()) && (topY + stampImage.getHeight() <= bi.getHeight())){
                        for(int i = topY; i < topY + stampImage.getHeight(); i++){
                            for(int j = topX; j < topX + stampImage.getWidth(); j++){
                                if(bi.getRGB(j, i) != stampImage.getRGB(j - topX, i - topY)){
                                    bi.setRGB(j, i, stampImage.getRGB(j - topX, i - topY));
                                }
                            }
                        }
                    }
                    image.setFullImage(createGreenfootImageFromBI(bi));
                    image.redraw();
                    openFile.update (image.getDetails ());
                    checkForEdit();
                    resetCrop();
                }
                else if (!inCropOne){
                    MouseInfo first = Greenfoot.getMouseInfo();
                    firstPoint = new TextButton ("  ", 0, 10, false, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));
                    addObject(firstPoint, first.getX(), first.getY());
                    inCropOne = true;
                }
                else if (!inCropTwo){
                    MouseInfo second = Greenfoot.getMouseInfo();
                    secondPoint = new TextButton ("  ", 0, 10, false, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,10));                    
                    addObject(secondPoint, second.getX(), second.getY());
                    cropBox = new ColorButton(Color.BLACK, Math.abs(firstPoint.getX()-secondPoint.getX()), Math.abs(firstPoint.getY()-secondPoint.getY()), 2, false, false);
                    addObject(cropBox, Math.min(firstPoint.getX(), secondPoint.getX())+((int)(Math.abs(firstPoint.getX()-secondPoint.getX())/2)), Math.min(firstPoint.getY(), secondPoint.getY())+((int)(Math.abs(firstPoint.getY()-secondPoint.getY())/2)));
                    //addObject(cropBox, 100, 100);
                    inCropTwo = true;
                }
            }
            else if (Greenfoot.mouseClicked(cropButton)){
                if(inCropOne && inCropTwo){
                    int topX = Math.min(firstPoint.getX(), secondPoint.getX())-(640-(int)(image.getRealWidth()/2)), topY = Math.min(firstPoint.getY(), secondPoint.getY())-(560-(int)(image.getRealHeight()/2));
                    int height = Math.abs(firstPoint.getY()-secondPoint.getY()), width = Math.abs(firstPoint.getX()-secondPoint.getX());
                    //width = 50;
                    //height = 50;
                    //topX = firstPoint.getX()-330;
                    //topY = 0;
                    image.setFullImage(createGreenfootImageFromBI(image.getBufferedImage().getSubimage(topX, topY, width, height)));
                    image.redraw();
                    openFile.update (image.getDetails ());
                    checkForEdit();
                    resetCrop();
                }
            }
            else if (Greenfoot.mouseClicked(stampButton)){
                resetCrop();
                openStampFile();

                if(stampImage != null){
                    if(!stamping) stamping = true;
                    else stamping = false;
                }
            }
            else if (Greenfoot.mouseClicked(openButton))
            {
                resetCrop();
                openFile ();
            }

            else if (Greenfoot.mouseClicked(saveButton))
            {
                resetCrop();
                saveFile ();
            }
            else if (Greenfoot.mouseClicked(recentFilesButton))
            {
                resetCrop();
                selectRecentlyOpenedFile();
            }

            else{
                resetCrop();
            }
        }
    }


    /**
     * Allows user to select a recently opened file graphically using JFrame. 
     * 
     * @author Ibrahim Rahman
     */
    private void selectRecentlyOpenedFile () {

        // Parent component of the dialog.
        JFrame recentFrame = new JFrame("Recent Files");

        // Creates a new Recent Files object. 
        RecentFiles recent = new RecentFiles();

        // New arraylist containing all the recent files the user has. If there is
        // no recently opened files, then the array will be blank. 
        ArrayList <String> fileList = recent.getRecentFiles();
        
        // Creates a new button group (for the radio buttons). 
        ButtonGroup bg = new ButtonGroup();

        // Creates a new radio button on the popup window for every recently opened file. 
        for (int i = 0; i < fileList.size(); i++) {

            File file = new File(fileList.get(i));
            JRadioButton rb = new JRadioButton(file.getName()); 
            rb.setBounds(25, 5 + (i*25), 300, 30);
            bg.add(rb);
            recentFrame.add(rb);
        }

        // Sets the location to null 
        recentFrame.setLocationRelativeTo(null);
        recentFrame.setSize(300,300);
        recentFrame.setResizable(false);    
        recentFrame.setLayout(null);    
        recentFrame.setVisible(true);
        recentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        // Open button (after user selects a file to open)
        JButton openRecentFileButton = new JButton("Open");  
        openRecentFileButton.setBounds(200,230,95,30);  
        recentFrame.add(openRecentFileButton);

        // Cancel button (for user to select when they want to exit/cancel selection)
        JButton cancelButton = new JButton("Cancel");  
        cancelButton.setBounds(100,230,95,30);  
        recentFrame.add(cancelButton);
        
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recentFrame.setVisible(false);
                recentFrame.dispose();
            }
        });

        openRecentFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                // https://stackoverflow.com/questions/201287/how-do-i-get-which-jradiobutton-is-selected-from-a-buttongroup
                for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
        
                    if (button.isSelected()) {

                        // get Full filename
                        for (int i = 0; i < fileList.size(); i++) {
                            File file = new File(fileList.get(i));
                            if (file.getName().equalsIgnoreCase(button.getText())) {
                                recentFrame.setVisible(false);
                                recentFrame.dispose();

                                if (image.openFile (file.getAbsolutePath(), file.getName()))
                                {
                                    openFile.update (image.getDetails ());
                                    recent.addRecentlyOpenedFile(file.getAbsolutePath());
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "This file does not exist");
                                }
                            }

                        }
                    }
                }
            }
        });

    }


    /**
     * Saves file as a PNG or JPEG. User is able to select the path of their file graphically
     * using JFrame. 
     * 
     * @throws IOException throws if file us unable to save. 
     * @author Ibrahim Rahman
     */
    private void saveFile () {
        try {
            // https://www.codejava.net/java-se/swing/show-save-file-dialog-using-jfilechooser
            // Parent component of the dialog.
            JFrame parentFrame = new JFrame();
            
            // Create a JFileChooser, a file chooser box 
            JFileChooser fileChooser = new JFileChooser();

            // Allows user to save their file as either PNG or JPEG. 
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG file", new String[] {"png"}));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG file", new String[] {"jpg"}));
            fileChooser.setAcceptAllFileFilterUsed(false);
            
            // If user does not select a file, display message.
            fileChooser.setDialogTitle("Specify a file to save");   
            
            //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // TODO: Not working
            int userSelection = fileChooser.showSaveDialog(parentFrame);
            
            // Saves file if user selects a valid option.
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                
                System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                fileName = fileToSave.getAbsolutePath();

                // https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
                // https://www.tabnine.com/code/java/methods/javax.swing.JFileChooser/getFileFilter
                // Saves file as JPEG.
                if (fileChooser.getFileFilter().getDescription().equalsIgnoreCase("JPEG file")) {

                    // Appends a .jpg extension to file name.
                    fileName += ".jpg";

                    // Creates a new Buffered Image. 
                    BufferedImage bi = image.getBufferedImage();
        
                    // https://stackoverflow.com/questions/16002167/using-imageio-write-to-create-a-jpeg-creates-a-0-byte-file
                    // Uses Buffered Image to create a JPEG file.
                    BufferedImage newBufferedImage = new BufferedImage(bi.getWidth(),
                    bi.getHeight(), BufferedImage.TYPE_INT_RGB);
                    newBufferedImage.getGraphics().drawImage(bi, 0, 0, null);
                    
                    // Creates a new file using the file name. 
                    File outputfile = new File(fileName);
                    
                    // https://docs.oracle.com/javase/tutorial/2d/images/saveimage.html
                    // Writes the Buffered Image to the file using ImageIO. 
                    ImageIO.write(newBufferedImage, "jpg", outputfile);
                }
                // Saves file as PNG. 
                else if (fileChooser.getFileFilter().getDescription().equalsIgnoreCase("PNG file")) {
                    
                    // Appends a .png extension to file name.
                    fileName += ".png";

                    // https://docs.oracle.com/javase/tutorial/2d/images/saveimage.html
                    // Creates a new file using the file name. 
                    File f = new File (fileName);  

                    // Writes the Buffered Image to the file using ImageIO. 
                    ImageIO.write(image.getBufferedImage(), "png", f); 
                }
                // If another file extension is chosen
                else {
                    System.out.println("Invalid file extension");
                }

            }

        } 
        catch (IOException e){
            // this code instead
            System.out.println("Unable to save file: " + e);
        }
    }

    /**
     * Allows the user to open a new PNG, JPEG, or GIF image file. Updates
     * the recently opened files when a new file is openend. 
     * 
     * @author Ibrahim Rahman
     */
    private void openFile ()
    {
        // Create a UI frame (required to show a UI element from Java.Swing)
        JFrame frame = new JFrame();

        // Create a JFileChooser, a file chooser box 
        JFileChooser fileChooser = new JFileChooser();

        // Add File filter for PNG, GIF, and JPEG. 
        // https://stackoverflow.com/questions/19302029/filter-file-types-with-jfilechooser
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG file", new String[] {"png"}));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("GIF file", new String[] {"gif"}));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG file", new String[] {"jpg", "jpeg"}));

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();

            // Opens the image
            if (image.openFile (selectedFile.getAbsolutePath(), selectedFile.getName()))
            {
                // Updates the image's details in the top left corner. 
                openFile.update (image.getDetails ());

                // Updates the recently opened files file. 
                RecentFiles recent = new RecentFiles();
                recent.addRecentlyOpenedFile(selectedFile.getAbsolutePath());
            }
        }
    }

    /**
     * Allows the user to open a new image file for the stamp. The stamp must be
     * either a PNG or JPEG and 32x32 pixels large. 
     * 
     * @author Ibrahim Rahman
     */
    private void openStampFile ()
    {
        // Create a UI frame (required to show a UI element from Java.Swing)
        JFrame frame = new JFrame();

        // Create a JFileChooser, a file chooser box 
        JFileChooser fileChooser = new JFileChooser();

        // Allows the user to only open PNG or JPEG files. 
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG file", new String[] {"png"}));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG file", new String[] {"jpg", "jpeg"}));

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){

            // Gets the file selected, and assigns it to an Image Holder object. 
            File selectedFile = fileChooser.getSelectedFile();
            ImageHolder stampFile = new ImageHolder(selectedFile.getAbsolutePath());

            // Ensures that a stamp file is 32x32 pixels. 
            if (stampFile.getRealHeight() != 32 || stampFile.getRealWidth() != 32) {
                JOptionPane.showMessageDialog(null, "Please select a image file that is 32x32");
            }
            // Deep copies the image to the stamp image. 
            else {
                stampImage = deepCopy(stampFile.getBufferedImage()); 
            }
            
        }
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
    
    private void checkForEdit(){
        if(editPos < edits.size()){
            edits.subList(editPos+1, edits.size()).clear();
        }
        edits.add(deepCopy(image.getBufferedImage()));
        editPos++;
    }
    
    private void resetCrop(){
        if(inCropOne){
            removeObject(firstPoint);                        
        }
        if(inCropTwo){
            removeObject(secondPoint);
            removeObject(cropBox);
        }
        inCropOne = false;
        inCropTwo = false;
    }
}

