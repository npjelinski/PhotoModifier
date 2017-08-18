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
	static int[] newimgarray;
	public static Color currentColor; // Wrapper for int color value of pixel
	public static Color newColor; // new color to convert back to RGB values
	
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		try
		{
			img = ImageIO.read(new File("rose.jpg"));
		}
		catch (IOException e) 
		{
			System.out.println("File not found.");
		}
		
		int width = img.getWidth(); //image width
		int height = img.getHeight(); //image height
			
		rgbarray = img.getRGB(0, 0, width, height, null, 0, width);
		
		newimgarray = new int[rgbarray.length];
		
		BufferedImage newimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		System.out.println("Enter 1 for Grayscale, 2 for Monocolor Red.");
		int selection = scan.nextInt();
		
		switch(selection)
		{
			case 1: grayFilter();
					break;
			case 2: monocolorRed();
					break;
		}
		
		try
		{
			newimg.setRGB(0, 0, width, height, newimgarray, 0, width); // re-create image
			File outputfile = new File("rose-red.jpg");
			ImageIO.write(newimg, "jpg", outputfile);
			System.out.println("Here");
		} 
		catch(IOException e) 
		{
			System.out.println("Cannot write file.");
		}
	}
	
	public static void grayFilter()
	{
		int newRed, newGreen, newBlue, newShade;
		for (int i = 0; i < rgbarray.length; i++)
		{
			currentColor = new Color(rgbarray[i]);
			
			newRed = (int) (.2989 * currentColor.getRed()); // take apart RGB value, apply formula
			newGreen = (int) (.587 * currentColor.getGreen());
			newBlue = (int) (.114 * currentColor.getBlue());
			
			newShade = newRed + newGreen + newBlue; // grayscale value
			newColor = new Color(newShade, newShade, newShade);
			newimgarray[i] = newColor.getRGB(); // pull RGB value
		}
	}
	
	public static void monocolorRed()
	{
		int newRed, newGreen, newBlue, newShade;
		for (int i = 0; i < rgbarray.length; i++)
		{
			currentColor = new Color(rgbarray[i]);
			if (currentColor.getRed() <= 150 || currentColor.getGreen() >= 100)
			{
				newRed = (int) (.2989 * currentColor.getRed()); // take apart RGB value, apply formula
				newGreen = (int) (.587 * currentColor.getGreen());
				newBlue = (int) (.114 * currentColor.getBlue());
				
				newShade = newRed + newGreen + newBlue; // grayscale value
				newColor = new Color(newShade, newShade, newShade);
				newimgarray[i] = newColor.getRGB(); // pull RGB value
			}
			else
			{
				newimgarray[i] = rgbarray[i];
			}
		}
	}
}
