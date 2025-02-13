package ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

/**
 * Converts a given 28x28 pixel image to an integer double array to match csv format in dataset
 *
 * Reference to https://www.dyclassroom.com/image-processing-project/how-to-get-and-set-pixel-value-in-java
 * @author Raymond
 *
 */
public class ImageToCSV {
	
	public static int[] imagee = new int[784];
	
	public static int[] getAR() {
		return imagee;
	}
	
	
	/**
	 * Takes in a 28x28 image and converts it to a 2-D Array of integers, where each integer denotes it�s RGB pixel value.
	 * @param img 28x28 image of any type to be resized
	 * @return img_array 2-D Array of integers size [28][28], where each integer denotes its corresponding RGB pixel value. 
	 */
	public static int[][] img_to_csv(BufferedImage img){
		int [][] img_array = new int[28][28];

		//save alpha values for each pixel
		//convert RGB values to alpha values to differentiate number, assuming colours closer to white are backround
		//and colours closer to black as the handwritten number
		//need to convert because for real images taken from phone, transparency will be at minimum for all pixels
		//(no differentiation between background and foreground)
		for (int y = 0; y <= 27; y++) {
			for (int x = 0; x <= 27; x++) {
				int pixel = img.getRGB(x, y);

				int r = (pixel>>16) & 0xff;
				int g = (pixel>>8) & 0xff;
				int b = pixel & 0xff;

				int rgb_avg = (r+g+b)/3;

				//System.out.println(r);

				int a = 255 - rgb_avg;
				img_array[y][x] = a;
			}
		}
		return img_array;
	}
	
	/**
	 * Takes in a 2-D integer array and converts it into an image of 28x28 pixels
	 * @param img_array Passed in 2-D integer array where each integer value denotes it�s according pixel RGB value
	 * @return img 28x28 image that should be the same as the original image before it was converted into an array.

	 */
	public static BufferedImage csv_to_img(int [][] img_array){
		BufferedImage img = new BufferedImage(28,28,5);

		//save alpha values for each pixel
//		//convert RGB values to alpha values to differentiate number, assuming colours closer to white are backround
//		//and colours closer to black as the handwritten number
//		//need to convert because for real images taken from phone, transparency will be at minimum for all pixels
//		//(no differentiation between background and foreground)
	for (int y = 0; y <= 27; y++) {
		for (int x = 0; x <= 27; x++) {
			int r = img_array[y][x];
			int g = img_array[y][x];
			int b = img_array[y][x];
			int a = img_array[y][x];

			int p = (a<<24) | (r<<16) | (g<<8) | b;
		    img.setRGB(x, y, p);
		}
	}
	return img;
	}
	
	public static void mai(String fName) {
	    BufferedImage img = null;
	    File f = null;
	    
	    int pix;

	    //read image
	    try{
	      f = new File("data/" + fName + ".png");
	      img = ImageIO.read(f);
	    }catch(IOException e){
	      System.out.println(e);
	    }

	    int [][] img_csv = img_to_csv(img);

	    for (int y = 0; y <= 27; y++) {
			for (int x = 0; x <= 27; x++) {
				pix = img_csv[y][x];
				imagee[28*y + x] = pix;
			}
		}

//	    BufferedImage new_img = csv_to_img(img_csv);
//
//	    try{
//	        f = new File("data/CUSEn9ne.csv");
//	        ImageIO.write(new_img, "png", f);
//	      }catch(IOException e){
//	        System.out.println(e);
//	    }

	}

}
