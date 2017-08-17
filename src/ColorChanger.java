import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.Color;

public class ColorChanger
{
	static BufferedImage img; // The raw image data
	
	static int[] rgbarray; // The 1D array of pixels
	public static Color currentColor; // Wrapper for int color value of pixel
	public static Color newColor; // new color to convert back to RGB values
	
	public static void main(String[] args) 
	{
		try
		{
			img = ImageIO.read(new File(new String(args[0])));
		}
		catch (IOException e) 
		{
			System.out.println("File not found.");
		}
		
		int width = img.getWidth(); //image width
		int height = img.getHeight(); //image height
			
		rgbarray = img.getRGB(0, 0, width, height, null, 0, width);
		
		int[] newimgarray = new int[rgbarray.length];
		
		for (int i = 0; i < rgbarray.length; i++)
		{
			currentColor = new Color(rgbarray[i]);
			
			int newRed = (int) (.2989 * currentColor.getRed()); // take apart RGB value, apply formula
			int newGreen = (int) (.587 * currentColor.getGreen());
			int newBlue = (int) (.114 * currentColor.getBlue());
			
			int newShade = newRed + newGreen + newBlue; // grayscale value
			newColor = new Color(newShade, newShade, newShade);
			newimgarray[i] = newColor.getRGB(); // pull RGB value
		}
		
		BufferedImage newimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		newimg.setRGB(0, 0, width, height, newimgarray, 0, width); // re-create image
		
		try
		{
			File outputfile = new File(new String(args[1]));
			ImageIO.write(newimg, "jpg", outputfile);
		} 
		catch(IOException e) 
		{
			System.out.println("Cannot write file.");
		}
	}
}
