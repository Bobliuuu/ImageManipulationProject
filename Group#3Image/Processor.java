// Import all required packages
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;
import java.awt.Color;
import java.math.BigInteger;
import java.lang.Math;
import greenfoot.*;

/**
 * Processor class - The class that processes the desigred image. 
 * <p>
 * This class manipulates Java BufferedImages, which are effectively 2d arrays
 * of pixels. Each pixel is a single integer packed with 4 values inside it. 
 * </p>
 * <p>
 * This class contains many utility methods, each which manipulate the image in different ways. 
 * There are methods to colorify the image, add a filter to it, distort it, transform it, sharpen it, or even add hidden text to it! 
 * The documentation for each specific method can be found below. 
 * There are also two modular methods for dealing with bit shift operators. They are unpackPixel() and packagePixel(). The first
 * extracts the RGB and alpha values of a certain color integer (color to pixel conversion), and the second does the opposite by 
 * "packing" the four integers back into a color integer. 
 * </p>
 * 
 * @author Jerry Zhu, Matthew Gong, Jordan Cohen
 * @version December 2021
 */
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
     * 
     * @auther Jerry Zhu
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
                
                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Make the image more brown by making the image's pixel values closer to brown's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */
    public static void brown (BufferedImage bi)
    {
        colorify(bi, 102, 51, 10, 2, 2, 2);
    }
    
    /**
     * Make the image more gray by making the image's pixel values closer to gray's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */
    public static void gray (BufferedImage bi)
    {
        colorify(bi, 153, 153, 153, 2, 2, 2);
    }

    /**
     * Make the image more red by making the image's pixel values closer to red's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */
    public static void red (BufferedImage bi)
    {
        colorify(bi, 240, 10, 10, 2, 2, 2);
    }
    
    /**
     * Make the image more yellow by making the image's pixel values closer to yellow's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */
    public static void yellow (BufferedImage bi)
    {
        colorify(bi, 240, 240, 10, 2, 2, 2);
    }
    
    /**
     * Make the image more blue by making the image's pixel values closer to blue's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */
    public static void blue (BufferedImage bi)
    {
        colorify(bi, 10, 10, 240, 2, 2, 2);
    }
    
    /**
     * Make the image more purple by making the image's pixel values closer to purple's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */
    public static void purple (BufferedImage bi)
    {
        colorify(bi, 102, 10, 153, 2, 2, 2);
    }
    
    /**
     * Make the image more black by making the image's pixel values closer to black's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */
    public static void black (BufferedImage bi)
    {
        colorify(bi, 10, 10, 10, 2, 2, 2);
    }
    
    /**
     * Make the image more pink by making the image's pixel values closer to pink's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */
    public static void pink (BufferedImage bi)
    {
        colorify(bi, 240, 192, 203, 2, 2, 2);
    }
    
    /**
     * Make the image more orange by making the image's pixel values closer to orange's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */
    public static void orange (BufferedImage bi)
    {
        colorify(bi, 240, 102, 10, 2, 2, 2);
    }
    
    /**
     * Make the image more green by making the image's pixel values closer to green's RGB values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */
    public static void green (BufferedImage bi)
    {
        colorify(bi, 10, 204, 10, 2, 2, 2);
    }
    
    /**
     * Convert's each pixel's RGB value of the image to the average of the RGB values by using the grayscale algorithm
     * Reference: https://en.wikipedia.org/wiki/Grayscale
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */ 
    public static void grayScale(BufferedImage bi)
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
                
                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (grey, grey, grey, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Uses a weighted greyscale algorithm
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */ 
    public static void weightedGrayScale(BufferedImage bi)
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
                int grey = (int)((0.3 * red) + (0.59 * green) + (0.11 * blue));
                
                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (grey, grey, grey, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Sets each pixel's RGB value to it's negative value (255 - value)
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
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

                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Increases the RGB values of each pixel to make the image brighter
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
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

                // Use the packagePixel method to package the integers back into a single integer
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
     * 
     * @author Jerry Zhu
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

                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Adds a sepia filter to the image
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
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
                
                // Calculate sepia values
                int sepiaRed = (int)(0.393 * red + 0.769 * green
                                   + 0.189 * blue);
                int sepiaGreen = (int)(0.349 * red + 0.686 * green
                                         + 0.168 * blue);
                int sepiaBlue = (int)(0.272 * red + 0.534 * green
                                        + 0.131 * blue);
                
                red = Math.min(sepiaRed, 255);
                green = Math.min(sepiaGreen, 255);
                blue = Math.min(sepiaBlue, 255);
                
                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Adds a luminance filter to the image
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */ 
    public static void luminance(BufferedImage bi)
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
                
                // Calculate luminance values
                int lumRed = (int)(0.299 * red + 0.587 * green
                                   + 0.114 * blue);
                int lumGreen = (int)(0.598 * red - 0.274 * green
                                         - 0.322 * blue);
                int lumBlue = (int)(0.211 * red - 0.523 * green
                                        + 0.312 * blue);
                
                red = Math.min(lumRed, 255);
                green = Math.min(lumGreen, 255);
                blue = Math.min(lumBlue, 255);
                
                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Make image warmer by increasing it's red values while decreasing its blue and green values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
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

                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Make image cooler by increasing it's blue and green values while decreasing its red values
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
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

                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Make image more transparent by increasing it's alpha value
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
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

                // Use the packagePixel method to package the integers back into a single integer
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
     * 
     * @author Jerry Zhu
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
                
                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Blur the image by putting the pixel values closer together
     * Uses blur matrix: |1/9|1/9|1/9|1/9|1/9|1/9|1/9|1/9|1/9|
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
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
                
                // Start taking sum of blur matrix values
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

                // Use the packagePixel method to package the integers back into a single integer
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
     * 
     * @author Jerry Zhu
     */
    public static void gaussianBlur (BufferedImage bi)
    {
        // Initialize gaussian filter matrix
        int[] filter = {1, 2, 1, 2, 4, 2, 1, 2, 1};
        int filterWidth = 3;
        
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        int sum = IntStream.of(filter).sum(); // Get sum of kernel matrix
        
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
        
        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);
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
     * 
     * @author Jerry Zhu
     */ 
    public static void pixelate(BufferedImage bi, int pixelSize)
    {
        // Get image size to use in for loops
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
     * 
     * @author Jerry Zhu
     */
     public static void contrast (BufferedImage bi, double percentage)
    {
        // Get image size to use in for loops
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

                // Use the packagePixel method to package the integers back into a single integer
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
     * 
     * @author Jerry Zhu
     */
    public static void adjustHue (BufferedImage bi, float percentage)
    {
        // Get image size to use in for loops
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
     * 
     * @author Jerry Zhu
     */
     public static void swapRGB (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x, y);
                int[] rgbValues = unpackPixel (rgb);
                
                // Swap RGB values
                int alpha = rgbValues[0];
                int red = rgbValues[3];
                int green = rgbValues[1];
                int blue = rgbValues[2];

                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    /**
     * Distorts the image into a swirl using bilinear interpolation: 
     * https://stackoverflow.com/questions/225548/resources-for-image-distortion-algorithms
     * @param bi            The BufferedImage (passed by reference) to change
     * @param percentage    The percentage of adjustment
     * 
     * @author Jerry Zhu
     */
     public static void distort (BufferedImage bi, double a, double b)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        double x0 = 0.5 * (xSize  - 1);
        double y0 = 0.5 * (ySize - 1);
        
        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);
        
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
                
                double dx = x - x0;
                double dy = y - y0;
                double r = Math.sqrt(dx*dx + dy*dy);
                double angle = Math.PI / 256 * r;
                int tx = (int) (dx * Math.cos(angle) - dy * Math.sin(angle) + x0);
                int ty = (int) (dx * Math.sin(angle) + dy * Math.cos(angle) + y0);

                if (tx >= 0 && tx < xSize && ty >= 0 && ty < ySize){
                    newBi.setRGB(x, y, bi.getRGB(tx, ty));
                }
            }
        }
        
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                bi.setRGB(i, j, newBi.getRGB(i, j));
            }
        }        
    }
    
    /**
     * Distorts the imagine into a spherical shape
     * Credit: http://popscan.blogspot.com/2012/04/fisheye-lens-equation-simple-fisheye.html
     * 
     * @param bi    the image to be distorted
     * 
     * @author Jerry Zhu
     */
    public static void spherify (BufferedImage bi)
    {
        
        double xSize = bi.getWidth(); 
        double ySize = bi.getHeight();
        
        BufferedImage newBi = new BufferedImage((int)xSize, (int)ySize, 3);
        
        int[] srcpixels = bi.getRGB(0, 0, (int)xSize, (int)ySize, null, 0, (int)xSize);
        int[] dstpixels = new int[(int)xSize * (int)ySize];            
        for (int y = 0; y < ySize; y++) {                                
            double ny = ((2*y)/ySize)-1;                        
            double ny2 = ny*ny;                                
            for (int x = 0; x < xSize; x++) {                            
                double nx = ((2*x)/xSize)-1;                    
                double nx2 = nx*nx;
                double r = Math.sqrt(nx2+ny2);                
                if (0.0 <= r && r <= 1.0) {                            
                    double nr = Math.sqrt(1.0-r*r);            
                    nr = (r + (1.0-nr)) / 2.0;
                    if (nr<=1.0) {
                        double theta = Math.atan2(ny,nx);         
                        double nxn = nr*Math.cos(theta);        
                        double nyn = nr*Math.sin(theta);        
                        int x2 = (int)(((nxn+1)*xSize)/2.0);        
                        int y2 = (int)(((nyn+1)*ySize)/2.0);        
                        int srcpos = (int)(y2*xSize+x2);            
                        if (srcpos >= 0 & srcpos < xSize*ySize) {
                            dstpixels[(int)(y*xSize+x)] = srcpixels[srcpos];    
                        }
                    }
                }
            }
        }
        
        newBi.setRGB(0, 0, (int)xSize, (int)ySize, dstpixels, 0, (int)xSize);
        
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                bi.setRGB(x, y, newBi.getRGB(x, y));
            }
        }
    }
    
    /**
     * Distorts the image by adding randomized noise 
     * https://www.codeproject.com/Questions/98491/Adding-random-noise-to-an-image
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */ 
    public static void noise(BufferedImage bi, int density)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
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
                
                if ((Math.random() * 1000) < density){
                    red = 255;
                    blue = 255;
                    green = 255;
                }
                
                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB(x, y, newColour);
            }
        }
    }
    
    /**
     * Solarizes the image based on a threshold
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */ 
    public static void solarize(BufferedImage bi, int threshold)
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
                if (red < threshold){
                    red = 255 - red;
                }
                if (green < threshold){
                    green = 255 - green;
                }
                if (blue < threshold){
                    blue = 255 - blue;
                }

                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    public static int getSobelFilter(BufferedImage bi, int i, int j, int xSize, int ySize, int colorPosition){
        float sumX = 0;
        float sumY = 0;
    
        int gx[][] = {{-1,0,1}, {-2,0,2}, {-1,0,1}};
        int gy[][] = {{-1,-2,-1}, {0,0,0}, {1,2,1}}; 
        for (int k = i - 1, x = 0; k < (i + 2); k++, x++)
        {
            for (int l = j - 1, y = 0; l < (j + 2); l++, y++)
            {
                if (k < 0 || l < 0 || k >= xSize || l >= ySize)
                {
                    continue;
                }
                
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(k, l);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                if (colorPosition == 0)
                {
                    sumX += red * gx[x][y];
                    sumY += red * gy[x][y];
                }
                else if (colorPosition == 1)
                {
                    sumX += green * gx[x][y];
                    sumY += green * gy[x][y];
                }
                else
                {
                    sumX += blue * gx[x][y];
                    sumY += blue * gy[x][y];
                }
            }
        }
        int result = (int)Math.round( Math.sqrt((sumX * sumX) + (sumY * sumY)) );
        
        return result < 255 ? result : 255;
    }
    
    /**
     * Make the edges more defined using a sobel operator
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */ 
    public static void edges(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);
        
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
                
                red = getSobelFilter(bi, x, y, xSize, ySize, 0);
                green = getSobelFilter(bi, x, y, xSize, ySize, 1);
                blue = getSobelFilter(bi, x, y, xSize, ySize, 2);

                // Use the packagePixel method to package the integers back into a single integer
                int newColour = packagePixel (red, green, blue, alpha);
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
     * Removes randomized noise from the image and adds a Laplace Filter
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */ 
    public static void laplace(BufferedImage bi)
    {
        // Get image size to use in for loops
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

                int sumRed = red * 8;
                int sumGreen = green * 8;
                int sumBlue = blue * 8;
                
                for (int r = -1; r <= 1; r++)
                {
                    for (int c = -1; c <= 1; c++)
                    {
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
                
                // Use the packagePixel method to package the integers back into a single integer
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
     * Adds an emboss filter to the iamge
     * 
     * @param bi    The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */ 
    public static void emboss(BufferedImage bi)
    {
        // Get image size to use in for loops
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

                int sumRed = 0;
                int sumGreen = 0;
                int sumBlue = 0;
                
                for (int r = -1; r <= 1; r++)
                {
                    for (int c = -1; c <= 1; c++)
                    {
                        // Calls method in BufferedImage that returns R G B and alpha values
                        // encoded together in an integer
                        rgb = bi.getRGB(x + r, y + c);
        
                        // Call the unpackPixel method to retrieve the four integers for
                        // R, G, B and alpha and assign them each to their own integer
                        rgbValues = unpackPixel (rgb);
                        
                        if (c == -1){
                            sumRed -= rgbValues[1];
                            sumGreen -= rgbValues[2];
                            sumBlue -= rgbValues[3];
                        }
                        else if (c == 0 && (r == -1 || r == 1)){
                            
                        }
                        else {
                            sumRed += rgbValues[1];
                            sumGreen += rgbValues[2];
                            sumBlue += rgbValues[3];
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
                
                // Use the packagePixel method to package the integers back into a single integer
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
     * Encodes a string into a binary string
     * 
     * @param message     The string message to be encoded
     * @return String     The converted binary string
     * 
     * @author Jerry Zhu
     */
    public static String encodeMessage (String message) {
        String bitString = new BigInteger(message.getBytes()).toString(2);
        if (bitString.length() % 8 != 0){
            String zeroes = "";
            while ((bitString.length() + zeroes.length()) % 8 != 0) {
                zeroes = zeroes + "0";
            }
            bitString = zeroes + bitString;
        }
        return bitString;
    }
    
    /**
     * Encodes a binary string into an image
     * 
     * @param bit         The binary string to be encoded
     * @param bi          The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */
    public static void encodeImage (String bit, BufferedImage bi) {
        int pointer = bit.length() - 1; 
        for (int x = bi.getWidth()-1; x >= 0; x--) {
            for (int y = bi.getHeight()-1; y >= 0; y--) { 
                Color c = new Color(bi.getRGB(x,y)); 
                byte r = (byte) c.getRed(); 
                byte g = (byte) c.getGreen(); 
                byte b = (byte) c.getBlue(); 
                byte[] RGB = {r, g, b};
                byte[] newRGB = new byte[3];
                for (int i = 2; i >= 0; i--) { 
                    if (pointer >= 0) { 
                        int lsb;
                        if ((RGB[i] & 1) == 1) {
                                lsb = 1;
                        } 
                        else {
                                lsb = 0;
                        }
                        if (Character.getNumericValue(bit.charAt(pointer)) != lsb) {
                            if (lsb == 1) { 
                                newRGB[i] = (byte) (RGB[i] & ~(1));
                            } 
                            else { 
                                newRGB[i] = (byte) (RGB[i] | 1);
                            }
                        } 
                        else {
                            newRGB[i] = RGB[i];
                        }
                    } 
                    else { 
                        newRGB[i] = (byte) (RGB[i] & ~(1));
                    }
                    pointer--;
                }
                Color newColor = new Color(Byte.toUnsignedInt(newRGB[0]), Byte.toUnsignedInt(newRGB[1]), Byte.toUnsignedInt(newRGB[2]));
                bi.setRGB(x,y,newColor.getRGB());
            }
        }
    }
    
    /**
     * Decodes the regular string from a binary string
     * 
     * @param encoded     The encoded binary string recieved from the image
     * @return String     The regular decoded string
     * 
     * @author Jerry Zhu
     */
    public static String getMessage (String encoded) {
        int count = encoded.length()-1;
        StringBuilder message = new StringBuilder();
        int values = encoded.length()/8;
        byte[] ba = new byte[values];
        int arrayCount = values-1;
        while (arrayCount > 0) {
            StringBuilder bits = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                bits.insert(0, encoded.charAt(count-i));
            }
            byte b = (byte) Integer.parseInt(bits.toString(), 2);
            int x = Byte.toUnsignedInt(b);
            ba[arrayCount] = (byte) x;
            char c = (char) x;
            message.insert(0, c);
            count = count - 8;
            arrayCount--;
        }
        String fin = new String(ba);
        return fin;
    } 

    /**
     * Decodes the bit string from a BufferedImage
     * 
     * @param bi          The BufferedImage (passed by reference) to change
     * @return String     The decoded message in the form of a binary string
     * 
     * @author Jerry Zhu
     */
    public static String decodeMessage(BufferedImage bi) {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                Color c = new Color(bi.getRGB(x,y)); 
                byte r = (byte) c.getRed(); 
                byte g = (byte) c.getGreen(); 
                byte b = (byte) c.getBlue(); 
                byte[] RGB = {r, g, b};
                for (int i = 0; i < 3; i++) {
                    if ((RGB[i] & 1) == 1) { 
                        sb.append("1");
                    } 
                    else {
                        sb.append("0");
                    }
                }
            }
        }
        return sb.toString();
    }
    
    /**
     * Sharpens an image to make it more defined
     *
     * @param bi            The BufferedImage (passed by reference) to change
     * 
     * @author Jerry Zhu
     */
     public static void sharpen (BufferedImage bi)
    {
        // Get image size to use in for loops
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
                
                // Use the packagePixel method to package the integers back into a single integer
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
     * 
     * @author Matthew Gong
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
     * 
     * @author Matthew Gong
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
     * 
     * @author Matthew Gong
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
     * 
     * @author Matthew Gong
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
     * 
     * @author Jerry Zhu
     */
    public static int getDominantColor(BufferedImage bi) {
        // Get image size to use in for loops
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
     * 
     * @author Jerry Zhu
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
     * 
     * @author Jordan Cohen
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
     * @param rgbaValue The value of a single pixel as an integer, representing<br>
     *                  8 bits for red, green and blue and 8 bits for alpha:<br>
     *                  <pre>alpha   red     green   blue</pre>
     *                  <pre>00000000000000000000000000000000</pre>
     * @return int[4]   Array containing 4 shorter ints<br>
     *                  <pre>0       1       2       3</pre>
     *                  <pre>alpha   red     green   blue</pre>
     *                  
     * @author Jordan Cohen
     *                  
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
     * 
     * @author Jordan Cohen
     */
    public static int packagePixel (int r, int g, int b, int a)
    {
        int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
        return newRGB;
    }
}



