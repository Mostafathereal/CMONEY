package CMONEYPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class NewNetwork {
	
	private final static int[] sizes = new int[]{784, 16, 16, 10};   // manually setting the size of each layer of nodes
	private double[][][] weights;
	private double[][] biases;
	
	private double learnRate = 0.1;
	
	private double[][] activations; 
	
	
	
	private void setRandWeights() {
		
		for(int i = 0; i < weights.length; i++) {
			for(int j = 0; j < weights[i].length; j++) {
				for(int k = 0; k < weights[i][j].length; k++ ) {
					weights[i][j][k] = -1.0 + (2.0)* Math.random();
				}
			}
		}
	}
	
	private void setRandBiases() {
		
		for(int i = 0; i < biases.length; i++) {
			for(int j = 0; j < biases[i].length; j++) {
				biases[i][j] = -1.0 + 2.0*Math.random();
			}
		}	
	}
	
	public void trainNet(int in[][], int out[][], int epochs) {
		for(int i = 0; i < epochs; i++) {
			for(int j = 0; j < in.length; j++) {
				feedForward(in[i]);
				double[] err = new double[10];
				for(int l = 0; l < 10; l++) {
					err[l] = out[j][l] - activations[3][l];
				}
				backProp(err);
			}
		}
	}
	
	
	public double[] genOutputs(int num) {
		double[] outputs = new double[10];
		outputs[num] = 1;
		return outputs;
	}
	
	public void backProp(double[] errors) {
		double[] deltaOut = new double[this.sizes[3]];
		
		for (int i = 0; i < this.sizes[3]; i++) {
			deltaOut[i] = dSigmoid(this.activations[3][i]) * errors[i];
		}
		
		double[] deltaH2 = new double[this.sizes[2]];
		
		for (int i = 0; i < this.sizes[2]; i++) {
			for (int j = 0; j < this.sizes[3]; j++) {
				deltaH2[i] = dSigmoid(this.activations[2][i]) * deltaOut[j] * this.weights[2][i][j];
			}
		}
		
		double[] deltaH1 = new double[this.sizes[1]];
		
		for (int i = 0; i < this.sizes[1]; i++) {
			for (int j = 0; j < this.sizes[2]; j++) {
				deltaH1[i] = dSigmoid(this.activations[1][i]) * deltaH2[j] * this.weights[1][i][j];
			}
		}
		
		for (int i = 0; i < this.sizes[0]; i++) {
			for (int j = 0; j < this.sizes[1]; j++){
				this.weights[0][i][j] += learnRate * deltaH1[j] * this.activations[0][i];
			}
		}
		
		for (int i = 0; i < this.sizes[1]; i++) {
			for (int j = 0; j < this.sizes[2]; j++){
				this.weights[1][i][j] += learnRate * deltaH2[j] * this.activations[1][i];
			}
		}
		
		for (int i = 0; i < this.sizes[2]; i++) {
			for (int j = 0; j < this.sizes[3]; j++){
				this.weights[2][i][j] += learnRate * deltaOut[j] * this.activations[2][i];
			}
		}
		
	}
	
	public void feedForward(int[] inputs) {
		//input layer
		for(int i=0; i<this.sizes[0]; i++) {
			this.activations[0][i] = inputs[i];
		}
		
		//hidden layer 1
		for(int i=0; i<this.sizes[1]; i++) {
			this.activations[1][i] = 0.0;
			for(int j=0; j<this.sizes[0]; j++) {
				this.activations[1][j] += weights[0][j][i] * this.activations[0][j];
			}
			this.activations[1][i] = sigmoid(this.activations[1][i]);
		}
		
		//hidden layer 2
		for(int i=0; i<this.sizes[2]; i++) {
			this.activations[2][i] = 0.0;
			for(int j=0; j<this.sizes[0]; j++) {
				this.activations[2][j] += this.weights[1][j][i] * this.activations[1][j];
			}
			this.activations[2][i] = sigmoid(this.activations[2][i]);
		}
		
		//output layer
		for(int i=0; i<this.sizes[3]; i++) {
			activations[3][i] = 0.0;
			for(int j=0; j<this.sizes[2]; j++) {
				this.activations[3][i] += this.activations[2][j] * this.weights[2][j][i];
			}
			this.activations[3][i] = sigmoid(this.activations[3][i]);
		}
	}
	
	public static double sigmoid(double x) {
		double activation = 1 / (1 + Math.exp(x));
		return activation;
	}
	
	public double dSigmoid(double x) {
		return sigmoid(x) * (1 - sigmoid(x));
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
