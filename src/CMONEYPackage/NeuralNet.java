package CMONEYPackage;

import org.ejml.simple.SimpleMatrix;

public class NeuralNet {

	//public static SimpleMatrix matrix = new SimpleMatrix(4, 4);
	private final static int[] sizes = new int[]{784, 16, 16, 10};   // manually setting the size of each layer of nodes
	private final static int numLayers = sizes.length;
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
						
		for(int i = 0; i < 4; i++) {
			System.out.print(sizes[i] + "  ");
		}
		
		//matrix.print();

	}

}