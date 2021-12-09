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
    private int width;
    private int height;
    private int borderThickness;
    private Color color;
    private Color borderColor;
    
    /**
     * Basic constructor to create a color button
     * @param color           Base color of the color button
     */
    public ColorButton(Color color) {
        this.color = color;
        width = 20;
        height = 20;
        image = new GreenfootImage(20, 20);
        borderThickness = 2;
        borderColor = Color.WHITE;
        image.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            image.drawRect (0 + i, 0 + i, 20 - 1 - (i * 2), 20 - 1 - (i*2));
        }
        image.setColor(color);
        image.fillRect(2, 2, 16, 16);
        mouseOver = false;
        clickable = true;
        setImage(image);
    }
    
    /**
     * Similar to above, but with the ability to adjust width and height
     * @param color           Base color of the color button
     * @param width           The width of the image
     * @param height          The height of the image
     */
    public ColorButton(Color color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
        image = new GreenfootImage(width, height);
        borderThickness = 2;
        borderColor = Color.WHITE;
        image.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            image.drawRect (0 + i, 0 + i, width - 1 - (i * 2), height - 1 - (i*2));
        }
        image.setColor(color);
        image.fillRect(borderThickness, borderThickness, width - borderThickness * 2, height - borderThickness * 2);
        mouseOver = false;
        clickable = true;
        setImage(image);
    } 
    
    /**
     * Similar to above, but with the ability to set the button to animate on hover
     * @param color           Base color of the color button
     * @param width           The width of the image
     * @param height          The height of the image
     * @param isClickable     Whether the button supports UI animations or not
     */
    public ColorButton(Color color, int width, int height, boolean isClickable) {
        this.color = color;
        this.width = width;
        this.height = height;
        image = new GreenfootImage(width, height);
        borderThickness = 2;
        borderColor = Color.WHITE;
        image.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            image.drawRect (0 + i, 0 + i, width - 1 - (i * 2), height - 1 - (i*2));
        }
        image.setColor(color);
        image.fillRect(borderThickness, borderThickness, width - borderThickness * 2, height - borderThickness * 2);
        mouseOver = false;
        clickable = isClickable;
        setImage(image);
    } 
    
    /**
     * Similar to above, but with the ability to adjust border thickness
     * @param color           Base color of the color button
     * @param width           The width of the image
     * @param height          The height of the image
     * @param borderThickness The thickness of the border around the button
     * @param isClickable     Whether the button supports UI animations or not
     */
    public ColorButton(Color color, int width, int height, int borderThickness, boolean isClickable) {
        this.color = color;
        this.width = width;
        this.height = height;
        image = new GreenfootImage(width, height);
        borderColor = Color.WHITE;
        image.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            image.drawRect (0 + i, 0 + i, width - 1 - (i * 2), height - 1 - (i*2));
        }
        image.setColor(color);
        image.fillRect(borderThickness, borderThickness, width - borderThickness * 2, height - borderThickness * 2);
        mouseOver = false;
        clickable = isClickable;
        setImage(image);
    } 
    
    /**
     * Most sophisticated constructor, adding the ability to adjust border color
     * @param color           Base color of the color button
     * @param width           The width of the image
     * @param height          The height of the image
     * @param borderThickness The thickness of the border around the button
     * @param borderColor     The color of the button's border
     * @param isClickable     Whether the button supports UI animations or not
     */
    public ColorButton(Color color, int width, int height, int borderThickness, Color borderColor, boolean isClickable) {
        this.color = color;
        this.width = width;
        this.height = height;
        image = new GreenfootImage(width, height);
        image.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            image.drawRect (0 + i, 0 + i, 20 - 1 - (i * 2), 20 - 1 - (i*2));
        }
        image.setColor(color);
        image.fillRect(borderThickness, borderThickness, width - borderThickness * 2, height - borderThickness * 2);
        mouseOver = false;
        clickable = isClickable;
        setImage(image);
    } 
    
    /**
     * Update the image when the mouse is hovering over it by redrawing it and giving it a highlighted and enlarged effect
     */
    public void update() {
        highlightedImage = reDraw (color, color, Color.WHITE, 2);
        myImage = image;
    }
    
    /**
     * Redraw the highlighted and enlarged color button
     */
    public GreenfootImage reDraw (Color color, Color back, Color border, int pixelsLarger){
        // Get new width and hei
        int newWidth = width + pixelsLarger * 2;
        int newHeight = height + pixelsLarger * 2;
        GreenfootImage resultImage = new GreenfootImage(newWidth, newHeight);
        resultImage.setColor (borderColor);
        for (int i = 0; i < borderThickness; i++){
            resultImage.drawRect (0 + i, 0 + i, newWidth - 1 - (i * 2), newHeight - 1 - (i * 2));
        }
        resultImage.setColor(color);
        resultImage.fillRect(borderThickness, borderThickness, newWidth - borderThickness * 2, newHeight - borderThickness * 2);
        
        // Done!
        return resultImage;
    }
    
    public void deleteDescription() {
        
    }
    
    public void createDescription() {
        
    }
}
