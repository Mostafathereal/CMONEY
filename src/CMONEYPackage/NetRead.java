package CMONEYPackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ImageProcessing.ImageToCSV;
import ImageProcessing.MnistIReader;

public class NetRead {
	
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
		
		
		NewNetwork network = new NewNetwork();
		
		network.readAllW();
		network.read_biases();
		
	    MnistIReader R = (MnistIReader) new MnistIReader();

		// Testing data set of 10,000 images
		int[] labels = R.getLabels("data/t10k-labels.idx1-ubyte");
		List<int[][]> images = R.getImages("data/t10k-images.idx3-ubyte");
		
		int[][] image_set = new int[10000][784];  
		int[][] label_set = new int[10000][10];

		
	
		for(int i = 0; i < 10000; i++) {
			image_set[i] = arrConvert(images.get(i));
			label_set[i] = network.genOutputs(labels[i]); 
		}
		
		//System.out.print("\n\n --------------------  Done Mnist ---------------------------- \n\n");

	
		
		double counter = 0.0;
		
		for(int i = 0; i < 10000; i++) {
			if (network.evaluate(image_set[i]) == labels[i]){
				counter += 1;
			}
		}
		double res = counter / 10000;
		
		System.out.println("");
		System.out.println("--------------------------------------------------------------------------");
		System.out.print("AVG CORRECTNESS: " + res*100 + "%\n");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("");


		ImageToCSV ITC = new ImageToCSV();
		
		ITC.mai(args[0]);
		
		int[] img = ImageToCSV.getAR();
		//int[] img = image_set[3];
		
		
		for(int j = 0; j < 28; j++) {
			for(int i = 0; i < 28; i++) {
				System.out.printf("%-3d", img[28*j + i]);
			}
			System.out.println("\n");
		}
		System.out.println("YOUR NUMBER IS: " + network.evaluate(img) + " with " + network.evaluateConfidence() + "% confidence");
		
		
			
	}
	
}
	


