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


	private double[] b0 = new double[16];
	private double[] b1 = new double[16];
	private double[] b2 = new double[10];
	
	private double[] z1 = new double[16];
	private double[] z2 = new double[16];
	private double[] z3 = new double[10];
	
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
				}
				backProp(genOutputs(7));
			}
		}
	}
	
	
	public int[] genOutputs(int num) {
		int[] outputs = new int[10];
		outputs[num] = 1;
		return outputs;
	}
	
	/**
	 * Goes through the neural network backwards layer by layer calculating the error of each activation, 
	 * then adjusts the weights and biases based on the cost function derivative to train the neural network.
	 * @param outputs The array of values of the desired activations in the output layer.
	 */
	public void backProp(int[] outputs) {
		
		double[] delOutC = new double[this.act3.length];
		double[] outErr = new double[this.act3.length];
		
		//create cost derivative vector
		for (int i = 0; i < delOutC.length; i ++) {
			delOutC[i] = this.act3[i] - outputs[i];
		}
		
		//calculate error of output layer
		for (int i = 0; i < outErr.length; i ++) {
			outErr[i] = delOutC[i] * dSigmoid(this.z3[i]);
		}
		
		double[] h2Err = new double[this.act2.length];
		double[][] wt2 = transpose(this.w2);
		double[] intH2 = mult(wt2, outErr);
		
		//calculate error vector of second hidden layer
		for (int i = 0; i < h2Err.length; i ++) {
			h2Err[i] = intH2[i] * dSigmoid(this.z2[i]);
		}
		
		double[] h1Err = new double[this.act1.length];
		double[][] wt1 = transpose(this.w1);
		double[] intH1 = mult(wt1, h2Err);
		
		//calculate error vector of first hidden layer
		for (int i = 0; i < h1Err.length; i ++) {
			h1Err[i] = intH1[i] * dSigmoid(this.z1[i]);
		}
		
		
		//update biases in all layers
		for (int i = 0; i < this.b0.length; i++) {
			this.b0[i] += - h1Err[i];
		}
		
		for (int i = 0; i < this.b1.length; i++) {
			this.b1[i] += - h2Err[i];
		}
		
		for (int i = 0; i < this.b2.length; i++) {
			this.b2[i] += - outErr[i];
		}
		
		//update weights
		for (int i = 0; i < this.w0.length; i++) {
			for (int j = 0; j < this.w0[0].length; j++) {
				this.w0[i][j] += - (this.act0[j] * h1Err[i]);
			}
		}
		
		for (int i = 0; i < this.w1.length; i++) {
			for (int j = 0; j < this.w1[0].length; j++) {
				this.w1[i][j] += - (this.act1[j] * h2Err[i]);
			}
		}
		
		for (int i = 0; i < this.w2.length; i++) {
			for (int j = 0; j < this.w2[0].length; j++) {
				this.w2[i][j] += - (this.act2[j] * outErr[i]);
			}
		}
		
	}
	
	/**
	 * Transposes an input matrix.
	 * @param in The matrix to be transposed.
	 * @return Returns the transposed matrix.
	 */
	private double[][] transpose(double[][] in) {
		double[][] out = new double[in[0].length][in.length];
		
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in[0].length; j++) {
				out[j][i] = in[i][j];
			}
		}
		
		return out;
	}
	
	/**
	 * Computes the dot product of 2 vectors.
	 * @param a The first vector.
	 * @param b The second vector.
	 * @return Returns the result of the dot product of the 2 input vectors.
	 */
	private double dot(double[] a, double[] b) {
		double result = 0;
		
		for (int i = 0; i < a.length; i++) {
			result += a[i] * b[i];
		}
		
		return result;
	}
	
	/**
	 * Computes matrix multiplication of a matrix and a vector
	 * @param a A 2D matrix
	 * @param b An input vector
	 * @return Returns the vector which is the result of the matrix multiplication of a and b.
	 */
	private double[] mult(double[][] a, double[] b){
		double[] result = new double[a.length];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = dot(a[i], b);
		}
		
		return result;
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
				this.act1[i] += this.w0[i][j] * this.act0[j];
			}
			this.act1[i] = sigmoid(this.act1[i]);
		}
		
		//hidden layer 2
		for(int i=0; i<this.sizes[2]; i++) {
			this.act2[i] = 0.0;
			for(int j=0; j<this.sizes[1]; j++) {
				this.act2[i] += this.w1[i][j] * this.act1[j];
			}
			this.act2[i] = sigmoid(this.act2[i]);
		}
		
		//output layer
		for(int i=0; i<this.sizes[3]; i++) {
			act3[i] = 0.0;
			for(int j=0; j<this.sizes[2]; j++) {
				this.act3[i] += this.act2[j] * this.w2[i][j];
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
	
	public void write_weights() {

		File file = new File("data/weights.txt");

		
			FileWriter writer;
			try {
				writer = new FileWriter(file);
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 784; j++) {
						writer.write(Double.toString(this.w0[i][j]));
					}
					writer.write("\n");
				}
				
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 16; j++) {
						writer.write(Double.toString(this.w1[i][j]));
					}
					writer.write("\n");
				}
				
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 16; j++) {
						writer.write(Double.toString(this.w2[i][j]));
					}
					writer.write("\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
}
