package kaggle_reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class kaggle_reader {
	
	public static Integer [][] kag_reader_test(Integer line_num) throws FileNotFoundException {
		//returned array with 784 pixels
		//28 x 28 array
		Integer [][] img_array = new Integer[28][28];
		
		Scanner input = new Scanner(new File("data/test.csv"));
		
		StringTokenizer line = new StringTokenizer(input.nextLine(), ",");
		
		//increments to correct line number
		for (int i = 0; i < line_num; i++ ) {
			//split up line based on commas because is csv file
			line = new StringTokenizer(input.nextLine(), ",");
		}
		
		while (line.hasMoreTokens()) {
			for (int y = 0; y <= 27; y++) {
				System.out.print("line " + y + " ");
				
				for (int x = 0; x <= 27; x++) {
					img_array[y][x] = Integer.parseInt(line.nextToken());
					System.out.print(img_array[y][x]);
				}
				
				System.out.println();
				
			}
		}
		
		input.close();
		
		return img_array;
	}
	
	public static Integer [][] kag_reader_train(Integer line_num) throws FileNotFoundException {
		//returned array with 784 pixels
		//28 x 28 array
		Integer [][] img_array = new Integer[28][28];
		
		Scanner input = new Scanner(new File("data/train.csv"));
		
		StringTokenizer line = new StringTokenizer(input.nextLine(), ",");
		
		//increments to correct line number
		for (int i = 0; i < line_num; i++ ) {
			//split up line based on commas because is csv file
			line = new StringTokenizer(input.nextLine(), ",");
		}
		
		//print out label
		System.out.println("Label: " + line.nextToken());
		
		while (line.hasMoreTokens()) {
			for (int y = 0; y <= 27; y++) {
				System.out.print("line " + y + " ");
				
				for (int x = 0; x <= 27; x++) {
					img_array[y][x] = Integer.parseInt(line.nextToken());
					System.out.print(img_array[y][x]);
				}
				
				System.out.println();
				
			}
		}
		
		input.close();
		
		return img_array;
	}
	
	public static void main(String[] args) throws FileNotFoundException
    { 
  
        Integer[][] arr = kag_reader_train(19);
  
        for (int i = 0; i <= 27; i++) { 
            for (int j = 0; j <= 27; j++) { 
                System.out.print(arr[i][j] + " "); 
            } 
  
            System.out.println(); 
        } 
    } 

}
