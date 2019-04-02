package CMONEYPackage;

import java.util.Random;

import org.ejml.simple.SimpleMatrix;
import java.util.List;
import java.util.ArrayList;
import org.ejml.simple.*;
import java.io.Serializable;
import org.ejml.simple.SimpleBase;
import org.ejml.simple.SimpleMatrix;
import org.ejml.simple.*;



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
	
	public void backProp(int[] x, int y) {
		SimpleMatrix[] nabla_w = new SimpleMatrix[numLayers-1];
		SimpleMatrix[] nabla_b = new SimpleMatrix[numLayers-1];
		Random rand = new Random();
		
		int i = 0;
		for (SimpleMatrix A : this.biases) {
			nabla_w[i] = new SimpleMatrix(A.numRows(), A.numCols());
			i++;
		}
		
		i = 0;
		for (SimpleMatrix A : this.weights) {
			nabla_b[i] = new SimpleMatrix(A.numRows(), A.numCols());
			i++;
		}
		
		List<Double> Activation = new ArrayList<Double>();

		for(int j = 0; i < x.length; i++) {
			Activation.add((double) (x[i]));
		}
		
		ArrayList<List> activations = new ArrayList<List>();
		activations.add(Activation);
		
		ArrayList<int[]> zs = new ArrayList<int[]>();
				
		SimpleMatrix temp;
		double[] del = null;
		
		double z;
		SimpleMatrix actMat;
		for (SimpleMatrix A : this.biases) {
			
			i=0;
			for (double a : Activation) {
				del[i] = a;
				i++;
			}
						
			temp = new SimpleMatrix(Activation.size(), 1, true, del);

			z = this.weights[i].dot(temp) + this.biases[i].get(0);
			i++;
			
			
		}
		
		
		
		
		
		
		
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
		
		//SimpleMatrix[][] dn = backProp(miniBatch[0], miniBatch[1][0]);
		
		i = 0;
		for (SimpleMatrix A : nb) {
			nb[i] = A.plus(dn[0][i]);
			i++;
		}
		
		i = 0;
		for (SimpleMatrix A : nw) {
			nw[i] = A.plus(dn[1][i]);
			i++;
		}
		
		double rate = learnRate / miniBatch.length;
		
		i = 0;
		for (SimpleMatrix A : nb) {
			this.biases[i] = A.scale(rate).mult(this.biases[i]);
			i++;
		}
		
		i = 0;
		for (SimpleMatrix A : nw) {
			this.weights[i] = A.scale(rate).mult(this.weights[i]);
			i++;
		}
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