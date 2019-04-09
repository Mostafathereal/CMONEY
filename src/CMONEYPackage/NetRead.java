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

		
//	    int[][] kagLabels = new int[42000][10];
		int[] labels = R.getLabels("data/t10k-labels.idx1-ubyte");
		
		//int[][] kagImages = new int[42000][784];
		List<int[][]> images = R.getImages("data/t10k-images.idx3-ubyte");
		
		int[][] image_set = new int[10000][784];  
		int[][] label_set = new int[10000][10];

		
	
		for(int i = 0; i < 10000; i++) {
			image_set[i] = arrConvert(images.get(i));
			label_set[i] = network.genOutputs(labels[i]); 
		}
		
		//System.out.print("\n\n --------------------  Done Mnist ---------------------------- \n\n");
		
		
		
		
		//int[] input = arrConvert(images.get(10000));
//		int[] input2 = arrConvert(images.get(9999));
//		int[] input3 = arrConvert(images.get(9998));
//		int[] input4 = arrConvert(images.get(9997));
//		int[] input5 = arrConvert(images.get(9996));
//		int[] input6 = arrConvert(images.get(9995));
//		int[] input7 = arrConvert(images.get(9994));
//		int[] input8 = arrConvert(images.get(9993));
//		int[] input9 = arrConvert(images.get(9992));
//		int[] input10 = arrConvert(images.get(9991));
		
//		
//		System.out.println("\n" + labels[10000]);
//		System.out.println(network.evaluate(input));
		
		
//		System.out.println("\n" + labels[9999]);
//		System.out.println(network.evaluate(input2));
//		
//		
//		System.out.println("\n" + labels[9998]);
//		System.out.println(network.evaluate(input3));
//		
//		
//		System.out.println("\n" + labels[9997]);
//		System.out.println(network.evaluate(input4));
//		
//		
//		System.out.println("\n" + labels[9996]);
//		System.out.println(network.evaluate(input5));
//		
//		
//		System.out.println("\n" + labels[9995]);
//		System.out.println(network.evaluate(input6));
//		
//		
//		System.out.println("\n" + labels[9994]);
//		System.out.println(network.evaluate(input7));
//		
//		
//		System.out.println("\n" + labels[9993]);
//		System.out.println(network.evaluate(input8));
//		
//		
//		System.out.println("\n" + labels[9992]);
//		System.out.println(network.evaluate(input9));
//		
//		
//		System.out.println("\n" + labels[9991]);
//		System.out.println(network.evaluate(input10));

	
		
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
		System.out.println("YOUR NUMBER IS: " + network.evaluate(img));
		
		
			
	}
	
}
	


