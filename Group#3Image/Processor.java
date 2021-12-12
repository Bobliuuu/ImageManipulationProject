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
import java.util.stream.IntStream;
import java.awt.Color;
import java.lang.Math;
import greenfoot.*;

public class Processor  
{
    /**
     * General colorify method that will turn the RGB pixels of an image into a certain colour
     * Works for any color (R, G, B) and increment/decrement values
     * 
     * @param bi        The BufferedImage (passed by reference) to change
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
     * Make the image more brown by making the image's pixel values closer to brown's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     */
    public static void brown (BufferedImage bi)
    {
        colorify(bi, 102, 51, 10, 2, 2, 2);
    }
    
    /**
     * Make the image more gray by making the image's pixel values closer to gray's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     */
    public static void gray (BufferedImage bi)
    {
        colorify(bi, 153, 153, 153, 2, 2, 2);
    }

    /**
     * Make the image more red by making the image's pixel values closer to red's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     */
    public static void red (BufferedImage bi)
    {
        colorify(bi, 240, 10, 10, 2, 2, 2);
    }
    
    /**
     * Make the image more yellow by making the image's pixel values closer to yellow's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     */
    public static void yellow (BufferedImage bi)
    {
        colorify(bi, 240, 240, 10, 2, 2, 2);
    }
    
    /**
     * Make the image more blue by making the image's pixel values closer to blue's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     */
    public static void blue (BufferedImage bi)
    {
        colorify(bi, 10, 10, 240, 2, 2, 2);
    }
    
    /**
     * Make the image more purple by making the image's pixel values closer to purple's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     */
    public static void purple (BufferedImage bi)
    {
        colorify(bi, 102, 10, 153, 2, 2, 2);
    }
    
    /**
     * Make the image more black by making the image's pixel values closer to black's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     */
    public static void black (BufferedImage bi)
    {
        colorify(bi, 10, 10, 10, 2, 2, 2);
    }
    
    /**
     * Make the image more pink by making the image's pixel values closer to pink's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     */
    public static void pink (BufferedImage bi)
    {
        colorify(bi, 240, 192, 203, 2, 2, 2);
    }
    
    /**
     * Make the image more orange by making the image's pixel values closer to orange's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     */
    public static void orange (BufferedImage bi)
    {
        colorify(bi, 240, 102, 10, 2, 2, 2);
    }
    
    /**
     * Make the image more green by making the image's pixel values closer to green's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     */
    public static void green (BufferedImage bi)
    {
        colorify(bi, 10, 204, 10, 2, 2, 2);
    }
    
    /**
     * Convert's each pixel's RGB value of the image to the average of the RGB values
     * Uses the greyscale algorithm
     * 
     * @param bi    The BufferedImage (passed by reference) to change
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
     * @param bi    The BufferedImage (passed by reference) to change
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
     * @param bi    The BufferedImage (passed by reference) to change
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
     * @param bi    The BufferedImage (passed by reference) to change
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
     * @param bi    The BufferedImage (passed by reference) to change
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
     * @param bi    The BufferedImage (passed by reference) to change
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
     * @param bi    The BufferedImage (passed by reference) to change
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
     * @param bi    The BufferedImage (passed by reference) to change
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
                if (alpha > 0){
                    alpha -= 4; 
                    if (alpha < 0){
                        alpha = 0;
                    }
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Make image less transparent by decreasing it's alpha value
     * Less transparent means more opaque
     * 
     * @param bi    The BufferedImage (passed by reference) to change
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
                if (alpha < 250){
                    alpha += 4;
                    if (alpha > 250){
                        alpha = 250;
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
     * @param bi    The BufferedImage (passed by reference) to change
     */ 
    public static void fastBlur(BufferedImage bi)
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
     * Applies a gaussian blur effect to the image. Algorithm from Stack Overflow:
     * https://stackoverflow.com/questions/39684820/java-implementation-of-gaussian-blur 
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     */
    public static void gaussianBlur (BufferedImage bi)
    {
        int[] filter = {1, 2, 1, 2, 4, 2, 1, 2, 1};
        int filterWidth = 3;
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        int sum = IntStream.of(filter).sum();
        
        int[] input = bi.getRGB(0, 0, xSize, ySize, null, 0, xSize);

        int[] output = new int[input.length];
        
        int pixelIndexOffset = xSize - filterWidth;
        int centerOffsetX = filterWidth / 2;
        int centerOffsetY = filter.length / filterWidth / 2;
        
        for (int h = ySize - filter.length / filterWidth + 1, w = xSize - filterWidth + 1, y = 0; y < h; y++)
        {
            for (int x = 0; x < w; x++)
            {
                int red = 0;
                int green = 0;
                int blue = 0;

                for (int filterIndex = 0, pixelIndex = y * xSize + x; filterIndex < filter.length; pixelIndex += pixelIndexOffset)
                {
                    for (int fx = 0; fx < filterWidth; fx++, pixelIndex++, filterIndex++) {
                        int col = input[pixelIndex];
                        int[] values = unpackPixel(col);
                        int factor = filter[filterIndex];
                        
                        red += (values[1]) * factor;
                        green += (values[2]) * factor;
                        blue += (values[3]) * factor;
                    }
                }
                red /= sum;
                green /= sum;
                blue /= sum;
                
                output[x + centerOffsetX + (y + centerOffsetY) * xSize] = (red << 16) | (green << 8) | blue | 0xFF000000;
            }
        }
        BufferedImage newBi = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_ARGB);
        newBi.setRGB(0, 0, xSize, ySize, output, 0, xSize);
        
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                bi.setRGB(x, y, newBi.getRGB(x, y));
            }
        }
    }
    
    /**
     * Pixelate method to make an image less defined
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * @param percentage    The percentage of adjustment
     */ 
    public static void pixelate(BufferedImage bi, int pixelSize)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
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
                for (int xNew = x; (xNew < x + pixelSize) && (xNew < xSize); xNew++)
                {
                   for(int yNew = y; (yNew < y + pixelSize) && (yNew < ySize); yNew++) 
                   {
                     bi.setRGB(xNew, yNew, rgb);
                   }
                }
            }
        }
    }
    
    /**
     * Adjusts the contrast based on a percentage
     * @param bi            The BufferedImage (passed by reference) to change
     * @param percentage    The percentage of adjustment
     */
     public static void contrast (BufferedImage bi, double percentage)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x, y);
                int[] rgbValues = unpackPixel (rgb);
                
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                if (red < 240) {
                    red = (int)(percentage * (red - 128) + 128); 
                }
                if (blue < 240) {
                    blue = (int)(percentage * (blue - 128) + 128);
                }
                if (green < 240) {
                    green = (int)(percentage * (green - 128) + 128);
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /** 
     * Adjust the hues of the ased on a percentage. Method used: 
     * https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html#RGBtoHSB(int,%20int,%20int,%20float[])
     * 
     * @param bi            The BufferedImage (passed by reference) to change
     * @param percentage    The percentage of adjustment
     */
    public static void adjustHue (BufferedImage bi, float percentage)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x, y);
                int[] rgbValues = unpackPixel (rgb);
                
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                float[] hsbValues = Color.RGBtoHSB(red, green, blue, null);
                hsbValues[0] += percentage;
                int c = Color.HSBtoRGB(hsbValues[0], hsbValues[1], hsbValues[2]);
                
                bi.setRGB (x, y, c);
            }
        }
    }
    
    /**
     * Swaps the RGB values 
     * red -> blue, blue -> green, green -> red
     * @param bi            The BufferedImage (passed by reference) to change
     */
     public static void swapRGB (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x, y);
                int[] rgbValues = unpackPixel (rgb);
                
                int alpha = rgbValues[0];
                int red = rgbValues[3];
                int green = rgbValues[1];
                int blue = rgbValues[2];

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Sharpens an image to make it more defined
     * @param bi            The BufferedImage (passed by reference) to change
     */
     public static void sharpen (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);
        
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                if (x == 0 || x == xSize - 1 || y == 0 || y == ySize - 1){
                    newBi.setRGB(x, y, bi.getRGB(x, y));
                    continue;
                }
                
                int rgb = bi.getRGB(x, y);
                
                int[] rgbValues = unpackPixel (rgb);
                
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                int sumRed = red * 9;
                int sumGreen = green * 9;
                int sumBlue = blue * 9;
                
                for (int r = -1; r <= 1; r++)
                {
                    for (int c = -1; c <= 1; c++)
                    {
                        //if (x + r < 0 || x + r > xSize - 1 || y + c < 0 || y + c > ySize - 1){
                            //continue;
                        //}
    
                        // Calls method in BufferedImage that returns R G B and alpha values
                        // encoded together in an integer
                        rgb = bi.getRGB(x + r, y + c);
        
                        // Call the unpackPixel method to retrieve the four integers for
                        // R, G, B and alpha and assign them each to their own integer
                        rgbValues = unpackPixel (rgb);
                        
                        if (r != 0 || c != 0){
                            sumRed -= rgbValues[1];
                            sumGreen -= rgbValues[2];
                            sumBlue -= rgbValues[3];
                        }
                    }
                }

                if (sumRed > 255){
                    sumRed = 255; 
                }
                if (sumRed < 0){
                    sumRed = 0;
                }
                if (sumBlue > 255){
                    sumBlue = 255; 
                }
                if (sumBlue < 0){
                    sumBlue = 0;
                }
                if (sumGreen > 255){
                    sumGreen = 255; 
                }
                if (sumGreen < 0){
                    sumGreen = 0;
                }
                
                int newColour = packagePixel (sumRed, sumGreen, sumBlue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        
        for(int i = 0; i < xSize; i++){
            for(int j = 0; j < ySize; j++){
                bi.setRGB(i, j, newBi.getRGB(i, j));
            }
        }
    }
    
    /**
     * Rotates an image 90 degrees clockwise
     * 
     * @param bi    The BufferedImage (passed by reference) to change
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
     * @param bi    The BufferedImage (passed by reference) to change
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
     * @param bi    The BufferedImage (passed by reference) to change
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
     * Flips an image vertically
     * 
     * @param bi    The BufferedImage (passed by reference) to change
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
    
    /**
     * Gets the dominant color of a BufferedImage
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     */
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
    
    /**
     * Takes a BufferedImage and crops it to its desired size. Method used: 
     * https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html#getSubimage(int,%20int,%20int,%20int)
     * 
     * @param bi              The BufferedImage (passed by reference) to change
     * @return BufferedImage  The updated and cropped BufferedImage
     */
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
