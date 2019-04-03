package CMONEYPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class KagglReader {
	
	
	public static int [][] kag_reader_test(int line_num) throws FileNotFoundException {
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
	
	public static int [][] kag_reader_train(int line_num) throws FileNotFoundException {
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
	
	public static int kag_reader_train_label(int line_num) throws FileNotFoundException {
		
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
	
	public static void main(String[] args) throws FileNotFoundException { 
  
        System.out.println(kag_reader_train_label(19));
    } 
}
