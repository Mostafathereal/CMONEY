package CMONEYPackage;

import java.io.FileNotFoundException;

import ImageProcessing.KagglReader;

public class maintest {
	
	public static void main(String[] args){
		
		NewNetwork net = new NewNetwork(); 
		
		net.setRandWeights();
		int[][] image_set = new int[10000][784];  
		int[][] label_set = new int[10000][10];
		
		int[] input;
		try {
			input = NetworkMain.arrConvert(KagglReader.kag_reader_train(19));
			int label = KagglReader.kag_reader_train_label(19); 
			
			System.out.print("before training (soln is " + label + ") :");
			
			System.out.println(net.evaluate(input));
			
			int[][] output;
			
			for(int i = 1; i < 10000; i++) {
				
				image_set[i] = NetworkMain.arrConvert(KagglReader.kag_reader_train(i));
				label_set[i] = net.genOutputs(KagglReader.kag_reader_train_label(i)); 
				
			}
			
			net.trainNet(image_set, label_set, 10000);
			
			//System.out.println("length of data is " + labels.length);
			System.out.print("after training (soln is " + label + ") :");
			
			System.out.println(net.evaluate(input));
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
