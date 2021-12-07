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
        image.fillRect(0, 0, 9, 9);
        setImage(image);
        
    }    
    
}
