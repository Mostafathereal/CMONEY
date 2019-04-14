package ImageProcessing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Takes in an image of any size (even rectangular) and resizes it to be a 28 x 28 pixel 
 * image that would be an applicable size to be put through our neural network 
 * Taken from https://www.mkyong.com/java/how-to-resize-an-image-in-java/
 * @author Raymond Tu
 * @since 2019-03-07
 *
 */
public class ImageResizer {
	
	/**Takes in an image of any size (even rectangular) and resizes it to be a 28 x 28 pixel 
	 * image that would be an applicable size to be put through our neural network 
	 * @param originalImage Original passed in image to the method
	 * @param type Integer value that denotes the image format (png, jpg, etc)
	 * @return resizedImage Resized 28x28 image with the same original type
	 */
	public static BufferedImage resizeImage(BufferedImage originalImage, int type)
	{
		BufferedImage resizedImage = new BufferedImage(28, 28, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 28, 28, null);
		g.dispose();
			
		return resizedImage;
	}
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String [] args) throws IOException{
		BufferedImage originalImage = ImageIO.read(new File("data/IMG_7461.jpg"));
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		
		BufferedImage resizeImagePng = resizeImage(originalImage, type);
		ImageIO.write(resizeImagePng, "png", new File("data/Latest5.png")); 
	}

}

