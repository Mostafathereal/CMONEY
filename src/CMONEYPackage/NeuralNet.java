package CMONEYPackage;

import org.ejml.simple.SimpleMatrix;
import java.util.Random;



public class NeuralNet {

	//public static SimpleMatrix matrix = new SimpleMatrix(4, 4);
	private final static int[] sizes = new int[]{4, 16, 16, 10};   // manually setting the size of each layer of nodes
	private final static int numLayers = sizes.length;
	static SimpleMatrix[] weight;
	
	public void NeuralNet(){
		
		Random rand = new Random();
		weight = new SimpleMatrix[numLayers-1];
		
		for(int i = 0; i < numLayers - 1; i++) {
			weight[i] = SimpleMatrix.random(sizes[i+1],sizes[i],-1,1,rand);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		NeuralNet n = new NeuralNet();
		n.NeuralNet();
		System.out.println("hello");
		
		for(int i = 0; i < 3; i++) {
			n.weight[i].print();
		}
		

	}

}