package ImageProcessing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Resizes input image to 28x28 to be processed in neural network
 * 
 * Taken from https://www.mkyong.com/java/how-to-resize-an-image-in-java/
 * @author Raymond
 * @since 2019-03-07
 *
 */
public class image_resizer {
	
	/**
	 * 
	 * @param originalImage
	 * @param type
	 * @return
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
		BufferedImage originalImage = ImageIO.read(new File("data/sample_pictures/raymond_5_bold.png"));
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		
		BufferedImage resizeImagePng = resizeImage(originalImage, type);
		ImageIO.write(resizeImagePng, "png", new File("data/sample_pictures/test_5_bold-28x28.png")); 
	}

}
