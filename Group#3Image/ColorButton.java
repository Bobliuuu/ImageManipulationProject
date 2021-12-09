import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TextButtonPicture here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ColorButton extends Control
{
    private GreenfootImage image;
    private Color color;
    
    /**
     * Basic constructor to create a color button
     */
    public ColorButton(Color colour) {
        color = colour;
        image = new GreenfootImage(20, 20);
        int borderThickness = 2;
        Color borderColor = Color.BLACK;
        image.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            image.drawRect (0 + i, 0 + i, 20 - 1 - (i * 2), 20 - 1 - (i*2));
        }
        image.setColor(colour);
        image.fillRect(2, 2, 16, 16);
        mouseOver = false;
        clickable = true;
        setImage(image);
    }
    
    /**
     * Similar to above, but with the ability to adjust width and height
     */
    public ColorButton(Color colour, int width, int height, boolean isClickable) {
        color = colour;
        image = new GreenfootImage(width, height);
        int borderThickness = 2;
        Color borderColor = Color.BLACK;
        image.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            image.drawRect (0 + i, 0 + i, width - 1 - (i * 2), height - 1 - (i*2));
        }
        image.setColor(colour);
        image.fillRect(borderThickness, borderThickness, width - borderThickness, height - borderThickness);
        mouseOver = false;
        clickable = isClickable;
        setImage(image);
    } 
    
    /**
     * Similar to above, but with the ability to adjust border thickness
     */
    public ColorButton(Color colour, int width, int height, int borderThickness, boolean isClickable) {
        color = colour;
        image = new GreenfootImage(width, height);
        Color borderColor = Color.BLACK;
        image.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            image.drawRect (0 + i, 0 + i, width - 1 - (i * 2), height - 1 - (i*2));
        }
        image.setColor(colour);
        image.fillRect(borderThickness, borderThickness, width - borderThickness, height - borderThickness);
        mouseOver = false;
        clickable = isClickable;
        setImage(image);
    } 
    
    /**
     * Similar to above, but with the ability to adjust border color
     */
    public ColorButton(Color colour, int width, int height, int borderThickness, Color borderColor, boolean isClickable) {
        color = colour;
        image = new GreenfootImage(width, height);
        image.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            image.drawRect (0 + i, 0 + i, 20 - 1 - (i * 2), 20 - 1 - (i*2));
        }
        image.setColor(colour);
        image.fillRect(borderThickness, borderThickness, width - borderThickness, height - borderThickness);
        mouseOver = false;
        clickable = isClickable;
        setImage(image);
    } 
    
    public void update() {
        highlightedImage = drawTextBox (color, color, Color.WHITE, 2);
        myImage = image;
    }
    
    /**
     * 
     * Draw a text box.
     * 
     */
    public GreenfootImage drawTextBox (Color color, Color back, Color border, int pad){
        int height = image.getHeight() + (2 * pad);
        int width = image.getWidth() + (2 * pad);
        
        // Ensure an even width to keep text properly centered
        if (width % 2 == 1){
            width++;
        }

        // generate an image of the appropriate size
        GreenfootImage resultImage = new GreenfootImage(width, height);

        // fill in the background colour
        resultImage.setColor (back);
        resultImage.fill();

        // Draw the border
        resultImage.setColor (border); 
        resultImage.drawRect (0, 0, width-1, height-1);

        
        
        // Done!
        return resultImage;
    }
    
    public void deleteDescription() {
        
    }
    
    public void createDescription() {
        
    }
}
