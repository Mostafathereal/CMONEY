package CMONEYPackage;

import java.util.List;
import java.math.*;

import ImageProcessing.MnistIReader;

public class NetworkMain {
	
	
	public static int[] arrConvert(int arr[][]) {
		int[] x = new int[arr[0].length * arr.length];
		
		int counter = 0;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				x[counter] = arr[i][j];
			}
			counter++;
		}
		return x;
	}


	public static void main(String[] args) {
		NewNetwork net = new NewNetwork(); 
	    MnistIReader R = (MnistIReader) new MnistIReader();

		
		int[] labels = R.getLabels("data/t10k-labels.idx1-ubyte");
		
		List<int[][]> images = R.getImages("data/t10k-images.idx3-ubyte");
		
		int[][] image_set = new int[1000][784];  
		int[][] label_set = new int[1000][10];
 		
		for(int z = 0; z < 10; z++) {
			for(int j = 0; j < 28; j++) {
				for (int k = 0; k < 28; k++) {
					
					System.out.printf("%3s", images.get(z)[j][k]);
				}
				System.out.println();
			}
			
			System.out.println("");
			System.out.println("");
		}
		
		for(int i = 0; i < 500; i++) {
			
			image_set[i] = arrConvert(images.get(i));
			label_set[i] = net.genOutputs(labels[i]); 
			
			
		}
		
		
		
		 TODO Auto-generated method stub
		

		net.setRandWeights();
		
		System.out.print("before training (soln is " + labels[0] + ") :");
		
		int[] input = arrConvert(images.get(0));
		System.out.println(net.evaluate(input));
		
		net.trainNet(in, out, epochs);
		
		System.out.println("length of data is " + labels.length);
		
		
		System.out.print("after training (soln is " + labels[0] + ") :");
		

		
		
		net.trainNet(image_set, label_set, 500);
		
		for(int k = 0; k < 784; k++) {
			System.out.println(image_set[0][k]);
		}
		
		System.out.println(net.evaluate(input));

		
		
		
		

	}

}
