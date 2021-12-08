import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TextButtonPicture here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextButtonPicture extends Actor
{
    private GreenfootImage image;
        
    public TextButtonPicture(Color colour) {
        image = new GreenfootImage(10, 10);
        image.setColor(colour);
        image.fill();
        setImage(image);
    }    
    
    public TextButtonPicture(Color colour, int width, int height) {
        image = new GreenfootImage(width, height);
        image.setColor(colour);
        image.fill();
        setImage(image);
    } 
}
