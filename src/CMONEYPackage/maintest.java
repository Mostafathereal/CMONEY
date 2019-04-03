package CMONEYPackage;

import java.io.FileNotFoundException;

import ImageProcessing.KagglReader;

public class maintest {
	
	public static void main(String[] args){
		
		NewNetwork net = new NewNetwork(); 
		
		net.setRandWeights();
		
		int[] input;
		try {
			input = NetworkMain.arrConvert(KagglReader.kag_reader_train(19));
			int label = KagglReader.kag_reader_train_label(19); 
			
			System.out.print("before training (soln is " + label + ") :");
			
			System.out.println(net.evaluate(input));
			
			int[][] output;
			
			net.trainNet(input, out, epochs);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
