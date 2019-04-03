package CMONEYPackage;

import java.util.List;
import java.io.FileNotFoundException;
import java.math.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ImageProcessing.MnistIReader;

public class NetworkMain {
	
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


	public static void main(String[] args) throws FileNotFoundException {
		NewNetwork net = new NewNetwork(); 
		net.setRandWeights();
		net.setRandBiases();
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

		net.trainNet(image_set, label_set, 60000);
		
		System.out.print("\n\n --------------------  Done Mnist ---------------------------- \n\n");
		
		
		for(int i = 1; i < 100; i++) {
			kagLabels[i] = net.genOutputs(KagglReader.kag_reader_train_label(i));
			kagImages[i] = arrConvert(KagglReader.kag_reader_train(i));
		}
		
		
		
				
		System.out.print("\n\n --------------------  Done Loading Kaggle ---------------------------- \n\n");


		//net.trainNet(kagImages, kagLabels, 100);

		


		
		//System.out.print("before training (soln is " + labels[0] + ") :");
		
		int[] input = arrConvert(images.get(10000));
		int[] input2 = arrConvert(images.get(9999));
		int[] input3 = arrConvert(images.get(9998));
		int[] input4 = arrConvert(images.get(9997));
		int[] input5 = arrConvert(images.get(9996));
		int[] input6 = arrConvert(images.get(9995));
		int[] input7 = arrConvert(images.get(9994));
		int[] input8 = arrConvert(images.get(9993));
		int[] input9 = arrConvert(images.get(9992));
		int[] input10 = arrConvert(images.get(9991));
		
		
		//System.out.println(net.evaluate(input));
//		System.out.println("length of data is " + labels.length);	
//		System.out.print("after training (soln is " + labels[0] + ") :");
		
		
		//net.trainNet(kagImages, kagLabels, 1);
		
		
		System.out.println("\n" + labels[10000]);
		System.out.println(net.evaluate(input));
		
		
		System.out.println("\n" + labels[9999]);
		System.out.println(net.evaluate(input2));
		
		
		System.out.println("\n" + labels[9998]);
		System.out.println(net.evaluate(input3));
		
		
		System.out.println("\n" + labels[9997]);
		System.out.println(net.evaluate(input4));
		
		
		System.out.println("\n" + labels[9996]);
		System.out.println(net.evaluate(input5));
		
		
		System.out.println("\n" + labels[9995]);
		System.out.println(net.evaluate(input6));
		
		
		System.out.println("\n" + labels[9994]);
		System.out.println(net.evaluate(input7));
		
		
		System.out.println("\n" + labels[9993]);
		System.out.println(net.evaluate(input8));
		
		
		System.out.println("\n" + labels[9992]);
		System.out.println(net.evaluate(input9));
		
		
		System.out.println("\n" + labels[9991]);
		System.out.println(net.evaluate(input10));
		
		
		//System.out.println(images.size());	
		
		net.write_weights();

	}

}
