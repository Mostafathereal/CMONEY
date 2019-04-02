package CMONEYPackage;

import java.util.List;

import ImageProcessing.MnistIReader;

public class NetworkMain {
	
	public static MnistIReader R = (MnistIReader) new MnistIReader();
	
	public static int[] arrConvert(int arr[][]) {
		int[] x = new int[arr[0].length * arr.length];
		
		int counter = 0;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				x[counter] = arr[i][j];
			}
		}
		return x;
	}


	public static void main(String[] args) {
		
		int[] labels = R.getLabels("data/t10k-labels.idx1-ubyte");
		
		List<int[][]> images = R.getImages("data/t10k-images.idx3-ubyte");
		
		
		
		// TODO Auto-generated method stub
		
		NewNetwork net = new NewNetwork(); 

		net.setRandWeights();
		
		System.out.print("before training:");
		
		int[] input = arrConvert(images.get(0));
		System.out.println(net.evaluate(input));
		
		//net.trainNet(in, out, epochs);
		
		
		

	}

}
