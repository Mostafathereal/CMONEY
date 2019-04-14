package CMONEYPackage;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ImageProcessing.MnistIReader;

public class NetworkMain {
	
	/**
	 * helper method to convert a 2D array into a a 1D array
	 * @param arr The 2-D array to be converted
	 * @return A 1-D representation of the input array
	 */
	public static int[] arrConvert(int arr[][]) {
		int[] x = new int[arr[0].length * arr.length];
		
		int counter = 0;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				x[28*i + j] = arr[i][j];
			}
		}
		return x;
	}

	public static void main(String[] args) throws IOException {
		NewNetwork net = new NewNetwork(); 
				
	    MnistIReader R = (MnistIReader) new MnistIReader();

		
	    int[][] kagLabels = new int[42000][10];
		int[] labels = R.getLabels("data/train-labels.idx1-ubyte");
		
		int[][] kagImages = new int[42000][784];
		List<int[][]> images = R.getImages("data/train-images.idx3-ubyte");
		
		int[][] image_set = new int[60000][784];  
		int[][] label_set = new int[60000][10];

		
	
		for(int i = 0; i < 60000; i++) {
			image_set[i] = arrConvert(images.get(i));
			label_set[i] = net.genOutputs(labels[i]); 
		}

		net.trainNet(image_set, label_set, 100);
		
		System.out.print("\n\n --------------------  Done Mnist ---------------------------- \n\n");
		
		
		// the below commented code is to train with the kaggle data set, 
		// this takes a significant amount of time
//		for(int i = 1; i < 100; i++) {
//			kagLabels[i] = net.genOutputs(KagglReader.kag_reader_train_label(i));
//			kagImages[i] = arrConvert(KagglReader.kag_reader_train(i));
//		}
		//System.out.print("\n\n --------------------  Done Loading Kaggle ---------------------------- \n\n");
		//net.trainNet(kagImages, kagLabels, 100);

	
		
				
		// saving weights and biases to text fials
		net.write_weights();
		net.write_biases();

	}

}
