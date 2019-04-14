package ImageProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class reads in the Kaggle datasets, train.csv and test.csv, that will be used in training and testing our neural network.
 *  These csv files contain a large amount (42 000 images for train.csv and 28 000 images for test.csv) of images in csv(comma separated values)
 *  format. This class converts it into an integer array to be passed into the neural network.
 * @author Raymond Tu
 *
 */
public class KagglReader {
	
	/**
	 * Takes in the input line number in the test.csv file
	 *  and outputs the image in a 2-D 28x28 integer array to be used in the neural network.
	 * @param line_num Integer that represents the line number in the test.csv file, the row represents all pixels in the image
	 * @return img_array Returns 28x28 2-D integer array where every element represents the RGB pixel/alpha values
	 * @throws FileNotFoundException
	 */
	public int [][] kag_reader_test(int line_num) throws FileNotFoundException {
		//returned array with 784 pixels
		//28 x 28 array
		int [][] img_array = new int[28][28];
		
		Scanner input = new Scanner(new File("data/test.csv"));
		
		StringTokenizer line = new StringTokenizer(input.nextLine(), ",");
		
		//increments to correct line number
		for (int i = 0; i < line_num; i++ ) {
			//split up line based on commas because is csv file
			line = new StringTokenizer(input.nextLine(), ",");
		}
		
		while (line.hasMoreTokens()) {
			for (int y = 0; y <= 27; y++) {
				//System.out.print("line " + y + " ");
				
				for (int x = 0; x <= 27; x++) {
					img_array[y][x] = Integer.parseInt(line.nextToken());
					//System.out.print(img_array[y][x]);
				}
				
				//System.out.println();
				
			}
		}
		
		input.close();
		
		return img_array;
	}
	
	/**
	 * Takes in the input line number in the train.csv file and outputs
	 *  the image in a 2-D 28x28 integer array to be used in the neural network.
	 * @param line_num Integer that represents the line number in the train.csv file, the row represents all pixels in the image
	 * @return img_array Returns 28x28 2-D integer array where every element represents the RGB pixel/alpha values
	 * @throws FileNotFoundException
	 */
	public int [][] kag_reader_train(int line_num) throws FileNotFoundException {
		//returned array with 784 pixels
		//28 x 28 array
		int [][] img_array = new int[28][28];
		
		Scanner input = new Scanner(new File("data/train.csv"));
		
		StringTokenizer line = new StringTokenizer(input.nextLine(), ",");
		
		//increments to correct line number
		for (int i = 0; i < line_num; i++ ) {
			//split up line based on commas because is csv file
			line = new StringTokenizer(input.nextLine(), ",");
		}
		
		//print out label
		//System.out.println("Label: " + line.nextToken());
		line.nextToken();
		while (line.hasMoreTokens()) {
			for (int y = 0; y <= 27; y++) {
				//System.out.print("line " + y + " ");
				
				for (int x = 0; x <= 27; x++) {
					img_array[y][x] = Integer.parseInt(line.nextToken());
					//System.out.print(img_array[y][x]);
				}
				
				//System.out.println();
				
			}
		}
		
		input.close();

		return img_array;
	}
	
	/**
	 * Takes in the input line number in the train.csv file and outputs the label
	 *  (the intended numerical value that each image represents) for that line in the train.csv
	 * @param line_num Integer that represents the line number in the test.csv file, the row represents all pixels in the image
	 * @return label Returns a integer that represents the label for the image, which represents the numerical value that represents the intended value of the image
	 * @throws FileNotFoundException
	 */
	public int kag_reader_train_label(int line_num) throws FileNotFoundException {
		
		Scanner input = new Scanner(new File("data/train.csv"));
		
		StringTokenizer line = new StringTokenizer(input.nextLine(), ",");
		
		//increments to correct line number
		for (int i = 0; i < line_num; i++ ) {
			//split up line based on commas because is csv file
			line = new StringTokenizer(input.nextLine(), ",");
		}
		
		//print out label
		int label;
		
		label = Integer.parseInt(line.nextToken());
		//System.out.println("Label: " + label);
		
		input.close();

		return label;
	}
	
}
