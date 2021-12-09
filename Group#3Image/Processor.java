/**
 * Starter code for Processor - the class that processes images.
 * <p> This class manipulated Java BufferedImages, which are effectively 2d arrays
 * of pixels. Each pixel is a single integer packed with 4 values inside it.</p>
 * 
 * <p>All methods added to this class should be static. In other words, you do not
 *    have to instantiate (create an object of) this class to use it - simply call
 *    the methods with Processor.methodName and pass a GreenfootImage to be manipulated.
 *    Note that you do not have to return the processed image, as you will be passing a
 *    reference to your image, and it will be altered by the method, as seen in the Blueify
 *    example.</p>
 *    
 * <p>Some methods, especially methods that change the size of the image (such as rotation
 *    or scaling) may require a GreenfootImage return type. This is because while it is
 *    possible to alter an image passed as a parameter, it is not possible to re-instantiate it, 
 *    which is required to change the size of a GreenfootImage</p>
 * 
 * <p>
 * I have included two useful methods for dealing with bit-shift operators so
 * you don't have to. These methods are unpackPixel() and packagePixel() and do
 * exactly what they say - extract red, green, blue and alpha values out of an
 * int, and put the same four integers back into a special packed integer. </p>
 * 
 * @author Jordan Cohen 
 * @version November 2013
 */

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.Map;
import java.util.HashMap;
import greenfoot.*;

public class Processor  
{
    /**
     * General colorify method that will turn the RGB pixels of an image into a certain colour
     * Works for any color (R, G, B) and increment/decrement values
     * 
     * @param bi        The BufferedImage (passed by reference) to change.
     * @param colorR    The red value of the desired color 
     * @param colorG    The green value of the desired color 
     * @param colorB    The blue value of the desired color 
     * @param changeR   The change in the red value after one iteration of the method
     * @param changeG   The change in the green value after one iteration of the method
     * @param changeB   The change in the blue value after one iteration of the method
     */
    public static void colorify (BufferedImage bi, int colorR, int colorG, int colorB, int changeR, int changeG, int changeB)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        // Using array size as limit
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgba = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // Turn the picture closer to the actual colour by changing the RGB values to the parameters desired
                if (red < colorR){
                    red += changeR;
                    if (red > colorR){
                        red = colorR;
                    }
                }
                if (red > colorR){
                    red -= changeR;
                    if (red < colorR){
                        red = colorR;
                    }
                }
                if (blue < colorB){
                    blue += changeB;
                    if (blue > colorB){
                        blue = colorB;
                    }
                }
                if (blue > colorB){
                    blue -= changeB;
                    if (blue < colorB){
                        blue = colorB;
                    }
                }
                if (green < colorG){
                    green += changeG;
                    if (green > colorG){
                        green = colorG;
                    }
                }
                if (green > colorG){
                    green -= changeG;
                    if (green < colorG){
                        green = colorG;
                    }
                }
                
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Increase the blue value while decreasing the red and green values. 
     * 
     * Demonstrates use of packagePixel() and unpackPixel() methods.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static void blueify (BufferedImage bi)
    {
        colorify(bi, 50, 50, 253, 1, 1, 2);
    }

    /**
     * Convert's each pixel's RGB value of the image to the average of the RGB values
     * Uses the greyscale algorithm
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */ 
    public static void greyScale(BufferedImage bi)
    {

        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                // Calculates they greyscale value
                int grey = (blue + red + green)/3;
                
                // Turns the image closer to the greyscale image
                if(red > grey){
                    red -= 2;
                    if (red < grey){
                        red = grey;
                    }
                }
                if(red < grey){
                    red += 2;
                    if (red > grey){
                        red = grey;
                    }
                }
                if(green > grey){
                    green -= 2;
                    if (green < grey){
                        green = grey;
                    }
                }
                if(green < grey){
                    green += 2;
                    if (green > grey){
                        green = grey;
                    }
                }
                if(blue > grey){
                    blue -= 2;
                    if (blue < grey){
                        blue = grey;
                    }
                }
                if(blue < grey){
                    blue += 2;
                    if (blue > grey){
                        blue = grey;
                    }
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Sets each pixel's RGB value to it's negative value (255 - value)
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */ 
    public static void negative(BufferedImage bi)
    {

        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                // Find the negative (inverse) of each pixel's RGB values
                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Increases the RGB values of each pixel to make the image brighter
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */ 
    public static void brighten(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                // Increases the RGB values of the pixel
                if (red < 200){
                    red += 2;
                    if (red > 200){
                        red = 200;
                    }
                }
                if (green < 200){
                    green += 2;
                    if (green > 200){
                        green = 200;
                    }
                }
                if (blue < 200){
                    blue += 2;
                    if (blue > 200){
                        blue = 200;
                    }
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Decreases the red, green and blue values of each pixel to make
     * image darker
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */ 
    public static void darken(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                // Increases the RGB values of the pixel
                if (red > 50){
                    red -= 2;
                    if (red < 50){
                        red = 50;
                    }
                }
                if (green > 50){
                    green -= 2;
                    if (green < 50){
                        green = 50;
                    }
                }
                if (blue > 50){
                    blue -= 2;
                    if (blue < 50){
                        blue = 50;
                    }
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Turns the image closer to sepia 
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */ 
    public static void sepia(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                int sepiaRed = (int)(0.393 * red + 0.769 * green
                                   + 0.189 * blue);
                int sepiaGreen = (int)(0.349 * red + 0.686 * green
                                     + 0.168 * blue);
                int sepiaBlue = (int)(0.272 * red + 0.534 * green
                                    + 0.131 * blue);
                                    
                // Turn the picture closer to sepia
                if (red < sepiaRed){
                    red += 2;
                    if (red > sepiaRed){
                        red = sepiaRed;
                    }
                }
                if (red > sepiaRed){
                    red -= 2;
                    if (red < sepiaRed){
                        red = sepiaRed;
                    }
                }
                if (blue < sepiaBlue){
                    blue += 2;
                    if (blue > sepiaBlue){
                        blue = sepiaBlue;
                    }
                }
                if (blue > sepiaBlue){
                    blue -= 2;
                    if (blue < sepiaBlue){
                        blue = sepiaBlue;
                    }
                }
                if (green < sepiaGreen){
                    green += 2;
                    if (green > sepiaGreen){
                        green = sepiaGreen;
                    }
                }
                if (green > sepiaGreen){
                    green -= 2;
                    if (green < sepiaGreen){
                        green = sepiaGreen;
                    }
                }
                
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Make image warmer by increasing it's red values while decreasing its blue and green values
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */ 
    public static void warmer(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                // Makes the pixel warmer by increasing red value and decreasing blue and green values
                if (red < 200){
                    red += 2;
                    if (red > 200){
                        red = 200;
                    }
                }
                if (green > 50){
                    green -= 2;
                    if (green < 50){
                        green = 50;
                    }
                }
                if (blue > 50){
                    blue -= 2;
                    if (blue < 50){
                        blue = 50;
                    }
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Make image cooler by increasing it's blue and green values while decreasing its red values
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */ 
    public static void cooler(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                // Makes the pixel cooler by decreasing red value and increasing blue and green values
                if (red > 50){
                    red -= 2;
                    if (red > 50){
                        red = 50;
                    }
                }
                if (green < 200){
                    green += 2;
                    if (green > 200){
                        green = 200;
                    }
                }
                if (blue < 200){
                    blue += 2;
                    if (blue > 200){
                        blue = 200;
                    }
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Make image more transparent by increasing it's alpha value
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */ 
    public static void moreTransparent(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                // Makes the pixel more transparent
                if (alpha > 50){
                    alpha -= 2;
                    if (alpha < 50){
                        alpha = 50;
                    }
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Make image less transparent by decreasing it's alpha value
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */ 
    public static void lessTransparent(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                // Makes the pixel less transparent
                if (alpha < 200){
                    alpha += 2;
                    if (alpha > 200){
                        alpha = 200;
                    }
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Blur the image by putting the pixel values closer together
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */ 
    public static void blur(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                
                int rgb = bi.getRGB(x, y);
                int[] rgbValues = unpackPixel(rgb);
                int alpha = rgbValues[0];
                
                int sumBlue = 0;
                int sumGreen = 0;
                int sumRed = 0;
                int total = 0;
    
                for (int r = -1; r < 2; r++)
                {
                    for (int c = -1; c < 2; c++)
                    {
                        if (x + r < 0 || x + r > xSize - 1 || y + c < 0 || y + c > ySize - 1){
                            continue;
                        }
    
                        // Calls method in BufferedImage that returns R G B and alpha values
                        // encoded together in an integer
                        rgb = bi.getRGB(x + r, y + c);
        
                        // Call the unpackPixel method to retrieve the four integers for
                        // R, G, B and alpha and assign them each to their own integer
                        rgbValues = unpackPixel (rgb);
        
                        sumRed += rgbValues[1];
                        sumGreen += rgbValues[2];
                        sumBlue += rgbValues[3];

                        total++;
                    }
                }

                sumRed /= total;
                sumGreen /= total;
                sumBlue /= total;

                int newColour = packagePixel (sumRed, sumGreen, sumBlue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Pixelate method to make an image less defined
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */ 
    public static void pixelate(BufferedImage bi, int pixelSize)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x += pixelSize)
        {
            for (int y = 0; y < ySize; y += pixelSize)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                BufferedImage croppedImage = getCroppedImage(bi, x, y, pixelSize, pixelSize);
                int dominantColor = getDominantColor(croppedImage);
                
                for(int yd = y; (yd < y + pixelSize) && (yd < ySize); yd++) {
                    for(int xd = x; (xd < x + pixelSize) && (xd < xSize); xd++) {
                        bi.setRGB(xd, yd, dominantColor);
                    }
                }
            }
        }
    }
    
    /**
     * Rotates an image 90 degrees clockwise
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static BufferedImage rotate90Clockwise (BufferedImage bi){
        BufferedImage newBi = new BufferedImage (bi.getHeight(), bi.getWidth(), 3);

        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                newBi.setRGB(i, bi.getWidth() - 1 - j, bi.getRGB(j, i));
            }
        }
        
        return newBi;
    }
    
    /**
     * Rotates an image 90 degrees counterclockwise
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static BufferedImage rotate90CounterClockwise (BufferedImage bi){
        BufferedImage newBi = new BufferedImage (bi.getHeight(), bi.getWidth(), 3);

        newBi = rotate90Clockwise(bi);
        newBi = rotate90Clockwise(newBi);
        newBi = rotate90Clockwise(newBi);
        
        return newBi;
    }
    
    /**
     * Flips an image horizontally
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static void flipHorizontal (BufferedImage bi)
    {

        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);

        /**
         * INSERT TWO LOOPS HERE:
         * - FIRST LOOP MOVES DATA INTO A SECOND, TEMPORARY IMAGE WITH PIXELS FLIPPED
         *   HORIZONTALLY
         * - SECOND LOOP MOVES DATA BACK INTO ORIGINAL IMAGE
         * 
         * Note: Due to a limitation in Greenfoot, you can get the backing BufferedImage from
         *       a GreenfootImage, but you cannot set or create a GreenfootImage based on a 
         *       BufferedImage. So, you have to use a temporary BufferedImage (as declared for
         *       you, above) and then copy it, pixel by pixel, back to the original image.
         *       Changes to bi in this method will affect the GreenfootImage automatically.
         */ 
        
        for(int i = 0; i < ySize; i++){
            for(int j = 0; j < xSize; j++){
                newBi.setRGB((xSize-1)-j, i, bi.getRGB(j, i));
            }
        }
        
        for(int i = 0; i < ySize; i++){
            for(int j = 0; j < xSize; j++){
                bi.setRGB(j, i, newBi.getRGB(j, i));
            }
        }
    }
    
    /**
     * Flips and image vertically
     */
    public static void flipVertical(BufferedImage bi){
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);
        
        for(int i = 0; i < ySize; i++){
            for(int j = 0; j < xSize; j++){
                newBi.setRGB(j, ySize - 1 - i, bi.getRGB(j, i));
            }
        }
        
        for(int i = 0; i < ySize; i++){
            for(int j = 0; j < xSize; j++){
                bi.setRGB(j, i, newBi.getRGB(j, i));
            }
        }
    }
    
    public static int getDominantColor(BufferedImage bi) {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        Map<Integer, Integer> colorCount = new HashMap<>(100);
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                int rgb = bi.getRGB(x, y);
                if (colorCount.containsKey(rgb)){
                    colorCount.replace(rgb, colorCount.get(rgb) + 1);
                }
                else {
                    colorCount.put(rgb, 1);
                }
            }
        }
        
        int maxColor = 0;
        int maxCount = 0;
        
        for (Map.Entry<Integer, Integer> entry : colorCount.entrySet()){
            int color = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount){
                maxCount = count;
                maxColor = color;
            }
        }
        return maxColor;
    }
    
    public static BufferedImage getCroppedImage(BufferedImage bi, int startX, int startY, int width, int height) {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        if (startX < 0){
            startX = 0;
        }
        if (startY < 0){
            startY = 0;
        }
        if (startX > xSize){
            startX = xSize;
        }
        if (startX > ySize){
            startX = ySize;
        }
        if (startX + width > xSize){
            width = xSize - startX;
        }
        if (startY + height > ySize){
            height = ySize - startY;
        }
        return bi.getSubimage(startX, startY, width, height);
    }
    
    /**
     * Takes in a BufferedImage and returns a GreenfootImage.
     * 
     * @param bi The BufferedImage to convert.
     * 
     * @return GreenfootImage   A GreenfootImage built from the BufferedImage provided.
     */
    public static GreenfootImage createGreenfootImageFromBI (BufferedImage bi)
    {
        GreenfootImage returnImage = new GreenfootImage (bi.getWidth(), bi.getHeight());
        BufferedImage backingImage = returnImage.getAwtImage();
        Graphics2D backingGraphics = (Graphics2D)backingImage.getGraphics();
        backingGraphics.drawImage(bi, null, 0, 0);

        return returnImage;
    }

    /**
     * Takes in an rgb value - the kind that is returned from BufferedImage's
     * getRGB() method - and returns 4 integers for easy manipulation.
     * 
     * By Jordan Cohen
     * Version 0.2
     * 
     * @param rgbaValue The value of a single pixel as an integer, representing<br>
     *                  8 bits for red, green and blue and 8 bits for alpha:<br>
     *                  <pre>alpha   red     green   blue</pre>
     *                  <pre>00000000000000000000000000000000</pre>
     * @return int[4]   Array containing 4 shorter ints<br>
     *                  <pre>0       1       2       3</pre>
     *                  <pre>alpha   red     green   blue</pre>
     */
    public static int[] unpackPixel (int rgbaValue)
    {
        int[] unpackedValues = new int[4];
        // alpha
        unpackedValues[0] = (rgbaValue >> 24) & 0xFF;
        // red
        unpackedValues[1] = (rgbaValue >> 16) & 0xFF;
        // green
        unpackedValues[2] = (rgbaValue >>  8) & 0xFF;
        // blue
        unpackedValues[3] = (rgbaValue) & 0xFF;

        return unpackedValues;
    }
    
    /**
     * Takes in a red, green, blue and alpha integer and uses bit-shifting
     * to package all of the data into a single integer.
     * 
     * @param   int red value (0-255)
     * @param   int green value (0-255)
     * @param   int blue value (0-255)
     * @param   int alpha value (0-255)
     * 
     * @return int  Integer representing 32 bit integer pixel ready
     *              for BufferedImage
     */
    public static int packagePixel (int r, int g, int b, int a)
    {
        int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
        return newRGB;
    }
}
