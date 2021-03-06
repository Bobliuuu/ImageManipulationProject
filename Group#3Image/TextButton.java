import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Generic Button to display text that is clickable. Owned by a World, which controls click
 * capturing. Can also be used as a simple field to display text (set clickable to false).
 * 
 * Massively improved over the previous version! Now modular, supports highlighting, etc. To implement
 * highlighting, ensure that this is subclassed to Control.
 * 
 * @author Jordan Cohen
 * @version v0.6.0 (December 2021)
 */
public class TextButton extends Control
{
    // Declare private variables

    private String buttonText;

    private int padding;
    private int highlightPadding;
    private boolean fixedWidthEnabled;
    private boolean active; // ability to set and store inactive state
    private int fixedWidth;

    private Color textColor;
    private Color highlightedTextColor;
    private Color backColor;
    private Color highlightedBackColor;
    private Color disabledColor;
    private Color borderColor;
    private SuperTextBox description;

    private Font font;

    /**
     * Construct a TextButton with a given String at the default size
     * 
     * @param text  String value to display
     * 
     */
    public TextButton (String text)
    {
        this(text, new Font ("Comic Sans MS", false, false, 16));
        active = true;
    }

    
    
    /**
     * Construct a TextButton with a given String and a specified font
     * 
     * @param text  String value to display
     * @param font  the font of the text, as a Font
     * 
     */
    public TextButton (String text, Font font)
    {
        this (text, 8, true, font);
    }

    /**
     * Construct a TextButton with a given String and a specified padding and size and clickability
     * 
     * @param text  String value to display
     * @param padding  the padding around the text, as an integer
     * @param clickable  boolean denoting if the user can click it
     * @param font  the font of the text, as a Font
     */
    public TextButton (String text, int padding, boolean clickable, Font font){
        this (text,  padding, 0, clickable, new Color (40, 40, 250), new Color (80, 80, 250), new Color (245, 245, 245), new Color (255, 255, 255), Color.BLACK, font);  
    }

    /**
     * Primary constructor for objects of class TextButton - the most customizable version!
     * 
     * @param text
     * @param textSize
     * @param width         Width of desired button. Use 0 to automatically size around text.
     */
    public TextButton (String text, int padding, int width, boolean clickable, Color textColor, Color highlightedTextColor, Color backColor, Color highlightedBackColor, Color borderColor, Font font){

        this.textColor = textColor;
        this.highlightedTextColor = highlightedTextColor;
        this.backColor = backColor;
        this.highlightedBackColor = highlightedBackColor;
        this.borderColor = borderColor;

        this.font = font;
        
        disabledColor = new Color (100, 100, 100); // Grey

        fixedWidth = width;
        if (width == 0){
            fixedWidthEnabled = false;
        } else {
            fixedWidthEnabled = true; 
        }

        this.clickable = clickable;
        this.padding = padding;
        
        highlightPadding = padding + 2;
        buttonText = text;
        active = true;

        description = new SuperTextBox(getDescriptionText(), Color.DARK_GRAY, Color.WHITE, new Font("Comic Sans MS", false, false, 10), true, 90, 1, Color.WHITE);
        
        update();
        setImage(myImage);
    }

    /**
     * Function called upon the text box's addition to the world
     */
    public void addedToWorld (World w){
        super.addedToWorld (w);
        update();
    }
    
    /**
     * Changes the fixed width of the text box
     * 
     * @param width - the new int width
     */
    public void setFixedWidth (int width){
        fixedWidthEnabled = true;
        fixedWidth = width;
        update();
    }
    
    /**
     * Changes if the text box is active or not
     * 
     * @param active - boolean
     */
    public void setActive (boolean active)
    {
        this.active = active;
        update();
    }

    /**
     * Gets if the text box is active
     * 
     * @return boolean - Whether the box is active
     */
    public boolean isActive (){
        return active;
    }

    /**
     * Gets the amount of padding of the text box
     * 
     * @return int - the padding
     */
    public int getPadding (){
        return padding;
    }

    /**
     * This update method will change the displayed text.
     * 
     * @param   text    The text to display, as a String
     */
    public void update (String text){
        buttonText = text;
        update();
    }

    /**
     * Changes the color of the text box
     */
    public void setColor (Color text, Color highlighted){
        this.textColor = text;
        this.highlightedTextColor = highlighted;
        update();
    }
    
    /**
     * Changes the font of the text box
     */
    public void setFont (Font font){
        this.font = font;
        update();
    }

    /**
     * This primary update method will redraw this Actor's image based on current variable values
     */
    public void update ()
    {

        if (active)
            myImage = drawTextBox(buttonText, textColor, backColor, borderColor, padding);
        else
            myImage = drawTextBox(buttonText, disabledColor, backColor, Color.BLACK, padding);
        if (clickable)
            highlightedImage = drawTextBox (buttonText, highlightedTextColor, backColor, Color.BLACK, highlightPadding);
        else
            highlightedImage = null;

        if (clickable && mouseOver){
            setImage(highlightedImage);
        } else {
            setImage (myImage);
        }
    }

    /**
     * 
     * Draw a text box.
     * 
     */
    public GreenfootImage drawTextBox (String text, Color color, Color back, Color border, int pad){
        int height = font.getSize() + (3 * pad);
        int width;
        int textWidth = getStringWidth(font, text);
        if (fixedWidthEnabled){           
            width = fixedWidth;
        } else {
            width = textWidth + (pad * 2);//(font.getSize() * text.length()) + (pad * 2);
        }
        // Ensure an even width to keep text properly centered
        if (width % 2 == 1){
            width++;
        }

        // generate an image of the appropriate size
        GreenfootImage resultImage = new GreenfootImage(width, height);
        resultImage.setFont(font);

        // fill in the background colour
        resultImage.setColor (back);
        resultImage.fill();

        // Draw the border
        resultImage.setColor (border); 
        resultImage.drawRect (0, 0, width-1, height-1);

        // Draw the text
        resultImage.setColor (color);
        resultImage.drawString(text, (width - textWidth)/2, resultImage.getHeight() - pad * 2);//pad + font.getSize() + 1); // in y: +1 because border
        
        // Done!
        return resultImage;
    }

    /**
     * Adds the description box for the button below it
     * 
     * @author Matthew Gong
     */
    public void createDescription(){
        (getWorld()).addObject(description, getX(), getY() + 60);
    }
    
    /**
     * Removes the description box for the button
     * 
     * @author Matthew Gong
     */
    public void deleteDescription(){
        (getWorld()).removeObject(description);
    }
    
    /**
     * Returns the text of the description box depending on what the button text is
     * 
     * @return String[] - The lines of String of the description
     * @author Matthew Gong, Daniel Qian
     */
    public String[] getDescriptionText(){
        switch(buttonText){
            case "Undo":
                return new String[]{"Returns the image", "to the state", "before the", "last edit"};
            case "Redo":
                return new String[]{"Returns the image", "to the state", "before the", "last undo"};
            case "Reset":
                return new String[]{"Sets image back", "to the original"};
            case "Rotate Clockwise":
                return new String[]{"Turns the image", "90 degrees", "clockwise"};
            case "Rotate Counterclockwise":
                return new String[]{"Turns the image", "90 degrees", "counterclockwise"};
            case "Flip Horizontal":
                return new String[]{"Mirror-reversal", "of the original", "image across a", "horizontal axis"};
            case "Flip Vertical":
                return new String[]{"Mirror-reversal", "of the original", "image across a", "vertical axis"};
            case "Negative":
                return new String[]{"A total inversion", "in which, the", "light areas seem", "dark and", "vice-versa"};
            case "Brighten":
                return new String[]{"Turns the image", "brighter"};
            case "Darken":
                return new String[]{"Turns the image", "darker"};
            case "Open":
                return new String[]{"Open a new", "image"};
            case "Open Recent File":
                return new String[]{"Open a recent", "file"};
            case "Save":
                return new String[]{"Save the image"};
            case "More Transparent":
                return new String[]{"Makes the image", "more transparent"};
            case "Less Transparent":
                return new String[]{"Makes the image", "less transparent"};
            case "Pixelate":
                return new String[]{"Makes the image", "more pixelated"};
            case "Blur":
                return new String[]{"Makes the image", "blurrier"};
            case "Warmer":
                return new String[]{"Makes the image", "warmer"};
            case "Cooler":
                return new String[]{"Makes the image", "cooler"};
            case "Gaussian Blur":
                return new String[]{"Makes the image", "blurrier with a gaussian", "function"};
            case "Sepia":
                return new String[]{"Applies a sepia", "filter"};
            case "Change Contrast":
                return new String[]{"Increases the", "contrast of the", "image"};
            case "Change Hue":
                return new String[]{"Changes the hue", "of the image"};
            case "Swap RGB":
                return new String[]{"Swaps the RGB", "values of the image"};
            case "Sharpen":
                return new String[]{"Sharpens details in", "the image"};
            case "Crop":
                return new String[]{"Crop the image"};
            case "Greyscale":
                return new String[]{"Makes the image", "more grey"};
            case "Solarize":
                return new String[]{"Solarizes the image"};
            case "Noise":
                return new String[]{"Adds noise to", "the image"};
            case "Distort":
                return new String[]{"Distorts the image"};
            case "Stamp":
                return new String[]{"Add stamps of", "your choice to", "the image"};
            case "Emboss":
                return new String[]{"Adds an emboss", "filter to the image"};
            case "Refine Edges":
                return new String[]{"Refines the edges", "of the image"};
            case "Encode":
                return new String[]{"Encode a message", "in the image"};
            case "Decode":
                return new String[]{"Decode a message", "from the image"};
            case "Luminate":
                return new String[]{"Luminate", "the image"};
            case "Contrast":
                return new String[]{"Adjusts", "the contrast"};
            case "Spherify":
                return new String[]{"Spherifies", "the image"};
            case "Weighted Greyscale":
                return new String[]{"Applies a weighted", "greyscale to the", "image"};
            case "Rotate 180":
                return new String[]{"Rotates the image", "180 degrees"};
            case "Laplace":
                return new String[]{"Adds a Laplace", "filter"};
            default:
                return new String[]{"Unfinished"};
        }
    }
}
