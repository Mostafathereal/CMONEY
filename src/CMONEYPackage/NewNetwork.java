package CMONEYPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class NewNetwork {
	
	private final static int[] sizes = new int[]{784, 16, 16, 10};   // manually setting the size of each layer of nodes
	private double[][] w0 = new double[16][784];
	private double[][] w1 = new double[16][16];
	private double[][] w2 = new double[10][16];


	//private double[][] biases;
	
	private double learnRate;
	private double[] act0 = new double[784];
	private double[] act1 = new double[16];
	private double[] act2 = new double[16];
	private double[] act3 = new double[10];
	
	
	public void NewNetwork() {
		this.learnRate = 0.1;
		
	}
	
	public int evaluate(int inputs[]) {
		feedForward(inputs);
		double maxVal = act3[0];
		int maxInd = 0;
		for(int i = 1; i < 10; i++){
			if (act3[i] > maxVal){
				maxVal = act3[i];
				maxInd = i;
			}
		}
		return maxInd;
	}
	
	
	
	public void setRandWeights() {
		for(int i = 0; i < this.w0.length; i++) {
			for(int j = 0; j < this.w0[i].length; j++) {
				this.w0[i][j] = -1.0 + (2.0)* Math.random();
			}
		}
		
		for(int i = 0; i < this.w1.length; i++) {
			for(int j = 0; j < this.w1[i].length; j++) {
				this.w1[i][j] = -1.0 + (2.0)* Math.random();
			}
		}
		
		for(int i = 0; i < this.w2.length; i++) {
			for(int j = 0; j < this.w2[i].length; j++) {
				this.w2[i][j] = -1.0 + (2.0)* Math.random();
			}
		}
	}
	
//	private void setRandBiases() {
//		
//		for(int i = 0; i < biases.length; i++) {
//			for(int j = 0; j < biases[i].length; j++) {
//				biases[i][j] = -1.0 + 2.0*Math.random();
//			}
//		}	
//	}
	
	public void trainNet(int in[][], int out[][], int epochs) {
		for(int i = 0; i < epochs; i++) {
			for(int j = 0; j < in.length; j++) {
				feedForward(in[i]);
				double[] err = new double[10];
				for(int l = 0; l < 10; l++) {
					err[l] = out[j][l] - act3[l];
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
	
	private void backProp(double[] errors) {
		double[] deltaOut = new double[this.sizes[3]];
		
		for (int i = 0; i < this.sizes[3]; i++) {
			deltaOut[i] = dSigmoid(this.act3[i]) * errors[i];
		}
		
		double[] deltaH2 = new double[this.sizes[2]];
		
		for (int i = 0; i < this.sizes[2]; i++) {
			for (int j = 0; j < this.sizes[3]; j++) {
				deltaH2[i] = dSigmoid(this.act2[i]) * deltaOut[j] * this.w2[i][j];
			}
		}
		
		double[] deltaH1 = new double[this.sizes[1]];
		
		for (int i = 0; i < this.sizes[1]; i++) {
			for (int j = 0; j < this.sizes[2]; j++) {
				deltaH1[i] = dSigmoid(this.act1[i]) * deltaH2[j] * this.w1[i][j];
			}
		}
		
		for (int i = 0; i < this.sizes[0]; i++) {
			for (int j = 0; j < this.sizes[1]; j++){
				this.w0[i][j] += learnRate * deltaH1[j] * this.act0[i];
			}
		}
		
		for (int i = 0; i < this.sizes[1]; i++) {
			for (int j = 0; j < this.sizes[2]; j++){
				this.w1[i][j] += learnRate * deltaH2[j] * this.act1[i];
			}
		}
		
		for (int i = 0; i < this.sizes[2]; i++) {
			for (int j = 0; j < this.sizes[3]; j++){
				this.w2[i][j] += learnRate * deltaOut[j] * this.act2[i];
			}
		}
		
	}
	
	private void feedForward(int[] inputs) {
		//input layer
		for(int i=0; i<this.sizes[0]; i++) {
			this.act0[i] = inputs[i];
		}
		
		//hidden layer 1
		for(int i=0; i<this.sizes[1]; i++) {
			this.act1[i] = 0.0;
			for(int j=0; j<this.sizes[0]; j++) {
				this.act1[i] += w0[j][i] * this.act0[j];
			}
			this.act1[i] = sigmoid(this.act1[i]);
		}
		
		//hidden layer 2
		for(int i=0; i<this.sizes[2]; i++) {
			this.act2[i] = 0.0;
			for(int j=0; j<this.sizes[0]; j++) {
				this.act2[j] += this.w1[j][i] * this.act1[j];
			}
			this.act2[i] = sigmoid(this.act2[i]);
		}
		
		//output layer
		for(int i=0; i<this.sizes[3]; i++) {
			act3[i] = 0.0;
			for(int j=0; j<this.sizes[2]; j++) {
				this.act3[i] += this.act2[j] * this.w2[j][i];
			}
			this.act3[i] = sigmoid(this.act3[i]);
		}
	}
	
	private static double sigmoid(double x) {
		double activation = 1 / (1 + Math.exp(x));
		return activation;
	}
	
	private double dSigmoid(double x) {
		return sigmoid(x) * (1 - sigmoid(x));
	}
	
}
