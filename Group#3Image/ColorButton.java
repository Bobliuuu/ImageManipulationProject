import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TextButtonPicture here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ColorButton extends Actor
{
    private GreenfootImage image;
    
    /**
     * Basic constructor to create a color button
     */
    public ColorButton(Color colour) {
        image = new GreenfootImage(20, 20);
        int borderThickness = 2;
        Color borderColor = Color.BLACK;
        image.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            image.drawRect (0 + i, 0 + i, 20 - 1 - (i * 2), 20 - 1 - (i*2));
        }
        image.setColor(colour);
        image.fillRect(2, 2, 16, 16);
        setImage(image);
    }
    
    /**
     * Similar to above, but with the ability to adjust width and height
     */
    public ColorButton(Color colour, int width, int height) {
        image = new GreenfootImage(width, height);
        int borderThickness = 2;
        Color borderColor = Color.BLACK;
        image.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            image.drawRect (0 + i, 0 + i, width - 1 - (i * 2), height - 1 - (i*2));
        }
        image.setColor(colour);
        image.fillRect(borderThickness, borderThickness, width - borderThickness, height - borderThickness);
        setImage(image);
    } 
    
    /**
     * Similar to above, but with the ability to adjust border thickness
     */
    public ColorButton(Color colour, int width, int height, int borderThickness) {
        image = new GreenfootImage(width, height);
        Color borderColor = Color.BLACK;
        image.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            image.drawRect (0 + i, 0 + i, width - 1 - (i * 2), height - 1 - (i*2));
        }
        image.setColor(colour);
        image.fillRect(borderThickness, borderThickness, width - borderThickness, height - borderThickness);
        setImage(image);
    } 
    
    /**
     * Similar to above, but with the ability to adjust border color
     */
    public ColorButton(Color colour, int width, int height, int borderThickness, Color borderColor) {
        image = new GreenfootImage(width, height);
        image.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            image.drawRect (0 + i, 0 + i, 20 - 1 - (i * 2), 20 - 1 - (i*2));
        }
        image.setColor(colour);
        image.fillRect(borderThickness, borderThickness, width - borderThickness, height - borderThickness);
        setImage(image);
    } 
}
