package CMONEYPackage;

import org.ejml.simple.SimpleMatrix;
import java.util.Random;


public class NeuralNet {
	
	//size of each layer of network (4 layers)
	private final static int[] sizes = new int[]{4, 16, 16, 10};   // manually setting the size of each layer of nodes
	private final static int numLayers = sizes.length;
	
	//this array will hold three 2-dimensional matrices representing the weights between each of the layers
	static SimpleMatrix[] weights;
	
	//this matrix (or array of vectors) will hold the biases for each node of every layer, 
	//where each layer is represented by a row in the matrix 
	static SimpleMatrix[] biases;
	
	public void NeuralNet(){
		
		Random rand = new Random();
		weights = new SimpleMatrix[numLayers-1];
		biases = new SimpleMatrix[numLayers-1];
		
		//setting random values to weights & biases
		for(int i = 0; i < numLayers - 1; i++) {
			weights[i] = SimpleMatrix.random(sizes[i+1],sizes[i],-1,1,rand);
			biases[i] = SimpleMatrix.random(sizes[i+1],1,-1,1,rand);
		}
	}
	
	public static void backProp() {
		
	}
	
	public void updateMiniBatch(int[][] miniBatch, int learnRate) {
		
		SimpleMatrix[] nb = new SimpleMatrix[this.biases.length];
		SimpleMatrix[] nw = new SimpleMatrix[this.weights.length];
		
		int i = 0;
		for (SimpleMatrix A : this.biases) {
			nb[i] = new SimpleMatrix(A.numRows(), A.numCols());
			i++;
		}
		
		i = 0;
		for (SimpleMatrix A : this.weights) {
			nw[i] = new SimpleMatrix(A.numRows(), A.numCols());
			i++;
		}
		
//		for(i = 0; i < 3; i++) {
//			System.out.println("Testing");
//			nw[i].print();
//			nb[i].print();
//		}
		
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		NeuralNet n = new NeuralNet();
		n.NeuralNet();
		System.out.println("hello");
		
		for(int i = 0; i < 3; i++) {
			n.weights[i].print();
			n.biases[i].print();
		}
		
	
	}

}