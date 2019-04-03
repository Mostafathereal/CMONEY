package ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Converts a given 28x28 pixel image to an integer double array to match csv format in dataset
 *
 * Reference to https://www.dyclassroom.com/image-processing-project/how-to-get-and-set-pixel-value-in-java
 * @author Raymond
 *
 */
public class ImageToCSV {

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

				System.out.println(r);

				int a = 255-rgb_avg;
				img_array[y][x] = a;
			}
		}
		return img_array;
	}

	public static BufferedImage csv_to_img(int [][] img_array){
		BufferedImage img = new BufferedImage(28,28,5);

		//save alpha values for each pixel
		//convert RGB values to alpha values to differentiate number, assuming colours closer to white are backround
		//and colours closer to black as the handwritten number
		//need to convert because for real images taken from phone, transparency will be at minimum for all pixels
		//(no differentiation between background and foreground)
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

	public static void main(String args[])throws IOException{
	    BufferedImage img = null;
	    File f = null;

	    //read image
	    try{
	      f = new File("data/sample_pictures/raymond_5_bold-28x28.png");
	      img = ImageIO.read(f);
	    }catch(IOException e){
	      System.out.println(e);
	    }

	    int [][] img_csv = img_to_csv(img);

	    for (int y = 0; y <= 27; y++) {
			for (int x = 0; x <= 27; x++) {
				System.out.print(img_csv[y][x] + " ");
			}
			System.out.println();
		}

	    BufferedImage new_img = csv_to_img(img_csv);

	    try{
	        f = new File("data/sample_pictures/test-sraymond_5_bold-28x28-new1.png");
	        ImageIO.write(new_img, "png", f);
	      }catch(IOException e){
	        System.out.println(e);
	    }

	}

}
